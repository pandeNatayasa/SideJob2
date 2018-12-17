package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.DetailPekerjaanActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponsePekerjaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListPekerjaanAdminAdapter extends RecyclerView.Adapter<ListPekerjaanAdminAdapter.ViewHolder> {


    private List<ResponsePekerjaan> responsePekerjaans;
    private Context context;
    private CardView listDataPekerjaan;
    public String user_token;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nama_perusahaan, tv_pekerjaan;
        public ImageView btn_validasi, btn_hapus;
        public CardView card_data_jasa;
        public ApiService service;
        public DatabaseHelper mydb;

        public ViewHolder(View itemView) {
            super(itemView);

            context=itemView.getContext();

            service=ApiClient.getApiService();

            tv_nama_perusahaan=itemView.findViewById(R.id.tv_nama_user_inSubHomeAdmin);
            tv_pekerjaan=itemView.findViewById(R.id.tv_pekerjaan_inSubHomeAdmin);

            Toast.makeText(itemView.getContext(),"Hello ",Toast.LENGTH_SHORT).show();


            btn_validasi=itemView.findViewById(R.id.btn_validasi_inSubHomeAdmin);
            btn_hapus=itemView.findViewById(R.id.btn_delete_data_jasa_inSubHomeAdmin);
            SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
            user_token = sharedPref.getString("user_token","");

            listDataPekerjaan=itemView.findViewById(R.id.cardView_data_Jasa_inSubHomeAdmin);
        }
    }
    public ListPekerjaanAdminAdapter(List<ResponsePekerjaan> responsePekerjaans, Context context){
        this.responsePekerjaans=responsePekerjaans;
        this.context=context;
    }


    @NonNull
    @Override
    public ListPekerjaanAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType){
        View v = LayoutInflater.from(group.getContext())
                .inflate(R.layout.recycler_data_jasa_inadmin,group,false);
        return new ListPekerjaanAdminAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ListPekerjaanAdminAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(responsePekerjaans.get(position));

        ResponsePekerjaan responsePekerjaan = responsePekerjaans.get(position);
        Log.d("position in admin","ke-"+position);
        holder.tv_pekerjaan.setText(responsePekerjaan.getPekerjaan());
        holder.tv_nama_perusahaan.setText(responsePekerjaan.getNamaPerusahaan());

        Toast.makeText(holder.itemView.getContext(),"Hello ",Toast.LENGTH_SHORT).show();

        listDataPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id_data_jasa = responsePekerjaan.getId();
                String namapekerjaan = responsePekerjaan.getPekerjaan();
                String gaji_min = String.valueOf(responsePekerjaan.getGajiMin());
                String gaji_max = String.valueOf(responsePekerjaan.getGajiMax());
                String nama_perusahaan = responsePekerjaan.getNamaPerusahaan();
                String detail_pekerjaan = responsePekerjaan.getDetailPekerjaan();
                String syarat_pekerjaan = responsePekerjaan.getSyaratPekerjaan();


                Intent intent = new Intent(context,DetailPekerjaanActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("id_pekerjaan",id_data_jasa);
                bundle.putString("nama_pekerjaan", namapekerjaan);
                bundle.putString("gaji_min",gaji_min);
                bundle.putString("gaji_max",gaji_max);
                bundle.putString("nama_perusahaan",nama_perusahaan);
                bundle.putString("detail_pekerjaan",detail_pekerjaan);
                bundle.putString("syarat_pekerjaan",syarat_pekerjaan);

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pekerjaan = responsePekerjaan.getPekerjaan();

                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Really Delete")
                        .setMessage("Are you sure want to delete data jasa "+pekerjaan+" ?")
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.itemView.getContext(),"will be delete soon",Toast.LENGTH_SHORT).show();

//                                DataKategoriItem dataKategoriItem = (DataKategoriItem) holder.itemView.getTag();
//                                int id = dataKategoriItem.getId();
                                holder.service.delete_data_jasa(responsePekerjaan.getId(),user_token)
                                        .enqueue(new Callback<Response>() {
                                            @Override
                                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                                if (response.isSuccessful()){
//                                                    holder.mydb.deleteJasaOne(responsePekerjaan.getId());
                                                    responsePekerjaans.remove(responsePekerjaan);
                                                    notifyDataSetChanged();
                                                    Toast.makeText(holder.itemView.getContext(),"delete success",Toast.LENGTH_SHORT).show();
                                                }else {
                                                    Toast.makeText(holder.itemView.getContext(),"sukses tapi gagal",Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Response> call, Throwable t) {
                                                Toast.makeText(holder.itemView.getContext(),"error : "+t,Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        }).create().show();
            }
        });

        if (responsePekerjaan.getStatusValidasi().equals("non_valid")){
            holder.btn_validasi.setImageResource(R.drawable.ic_check_grey);
            holder.btn_validasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String pekerjaan = responsePekerjaan.getPekerjaan();
                    Toast.makeText(holder.itemView.getContext(),"user token "+user_token,Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("Really Validasi")
                            .setMessage("Are you sure want to validasi data pekerjaan "+pekerjaan+" ?")
                            .setNegativeButton(android.R.string.no,null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                DataKategoriItem dataKategoriItem = (DataKategoriItem) holder.itemView.getTag();
//                                int id = dataKategoriItem.getId();
                                    int id =responsePekerjaan.getId();
                                    Toast.makeText(holder.itemView.getContext(),"id Pekerjaan "+String.valueOf(id),Toast.LENGTH_SHORT).show();
                                    holder.service.updateDataJasaUser(id,user_token)
                                            .enqueue(new Callback<ResponsePekerjaan>() {
                                                @Override
                                                public void onResponse(Call<ResponsePekerjaan> call, retrofit2.Response<ResponsePekerjaan> response) {
                                                    if (response.isSuccessful()){
//                                                        holder.mydb.deleteJasaOne(responsePekerjaan.getId());
//                                                        holder.mydb.insertDataJasa(response.body().getId(),response.body().getIdKategori(),
//                                                                response.body().getIdUser(),response.body().getPekerjaan(),response.body().getUsia(),
//                                                                response.body().getNoTelp(),response.body().getEmail(),response.body().getStatus(),
//                                                                response.body().getStatusValidasi(),response.body().getAlamat(),response.body().getPengalamanKerja(),
//                                                                response.body().getEstimasiGaji());
                                                        responsePekerjaan.setStatusValidasi(response.body().getStatusValidasi());
                                                        notifyDataSetChanged();
                                                        holder.btn_validasi.setEnabled(false);
                                                        Toast.makeText(holder.itemView.getContext(),"success",Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        Toast.makeText(holder.itemView.getContext(),"sukses tapi gagal",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ResponsePekerjaan> call, Throwable t) {
                                                    Toast.makeText(holder.itemView.getContext(),"error :"+t,Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                            }).create().show();
                }
            });
        }else {
            holder.btn_validasi.setImageResource(R.drawable.ic_check_green_24dp);
        }

    }

    @Override
    public int getItemCount(){
        return responsePekerjaans.size();
    }
}
