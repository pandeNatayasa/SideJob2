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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.AddNewJobByUserActivity;
import com.tr.nata.projectandroid.DetailPekerjaanInProfillePerusahaanActivity;
import com.tr.nata.projectandroid.EditJobByUserActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.Response;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponsePekerjaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListDataJasaInUserAdapter extends RecyclerView.Adapter<ListDataJasaInUserAdapter.ViewHolder> {

    private List<ResponseDataJasaUser> dataJasaUsers;
    private List<ResponsePekerjaan> responsePekerjaans;
    private Context context;
    private CardView cardViewDataJasa;
    ImageView img_edit, img_delete;
    String id_kategori,id_user,pekerjaan,estimasi_gaji,pengalaman_kerja,
            usia,no_telp,email,status,alamat,id_kecamatan;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText et_id_kategori,et_pekerjaan,et_estimasi_gaji,et_pengalaman_kerja,
            et_usia,et_no_telp,et_email,et_status,et_alamat,et_id_kecamatan;
    ApiService service;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_pekerjaan,tv_gaji_min,tv_gaji_max;
        String token;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            context = itemView.getContext();
            service=ApiClient.getApiService();
            tv_gaji_max=itemView.findViewById(R.id.tv_gaji_max_profille);
            tv_gaji_min=itemView.findViewById(R.id.tv_gaji_min_profille);
            textView_pekerjaan=itemView.findViewById(R.id.tv_data_pekerjaan_perusahaan_inProfille);
            cardViewDataJasa=itemView.findViewById(R.id.cardView_data_jasa_inProfille);
            img_delete=itemView.findViewById(R.id.btn_delete_data_jasa_inProfille);
            img_edit=itemView.findViewById(R.id.btn_edit_data_jasa_inProfille);

            cardViewDataJasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ResponseDataJasaUser responseDataJasaUser=(ResponseDataJasaUser) itemView.getTag();
//                    SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
//
//                    int id_user_login = sharedPref.getInt("id_user_login",0);
//                    Toast.makeText(itemView.getContext(),"Pekerjaan : "+responseDataJasaUser.getPekerjaan(),Toast.LENGTH_SHORT).show();
//                    token = sharedPref.getString("user_token","");
//                    String nama = sharedPref.getString("nama_user_login","");
////                    String nama ="aa";
//                    String jasa = responseDataJasaUser.getPekerjaan();
//                    String gaji = String.valueOf(responseDataJasaUser.getEstimasiGaji());
//                    String usia = String.valueOf(responseDataJasaUser.getUsia());
//                    String tanggal_lahir = sharedPref.getString("tanggal_lahir_user_login","");
////                String tanggal_lahir = "aaa";
//                    String no_telp = String.valueOf(responseDataJasaUser.getNoTelp()) ;
//                    String email = responseDataJasaUser.getEmail();
//                    String status = responseDataJasaUser.getStatus();
//                    String pendidikan = responseDataJasaUser.getPengalamanKerja();
//                    String alamat = responseDataJasaUser.getAlamat();
//
//                    Toast.makeText(itemView.getContext(),"no_telp : "+no_telp,Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(context,DetailUserInAdminActivity.class);
////                    String namaKategori = dataKategoriItem.getKategori();
////                    int id = dataKategoriItem.getId();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nama", nama);
//                    bundle.putString("jasa",jasa);
//                    bundle.putString("gaji",gaji);
//                    bundle.putString("usia",usia);
//                    bundle.putString("tanggal_lahir",tanggal_lahir);
//                    bundle.putString("no_telp",no_telp);
//                    bundle.putString("email",email);
//                    bundle.putString("status",status);
//                    bundle.putString("pendidikan",pendidikan);
//                    bundle.putString("alamat",alamat);
////                    bundle.putInt("id_kategori",id);
//
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
                    ResponsePekerjaan responsePekerjaan = (ResponsePekerjaan) itemView.getTag();
                    int id_data_jasa = responsePekerjaan.getId();
                    String namapekerjaan = responsePekerjaan.getPekerjaan();
                    String gaji_min = String.valueOf(responsePekerjaan.getGajiMin());
                    String gaji_max = String.valueOf(responsePekerjaan.getGajiMax());
                    String nama_perusahaan = responsePekerjaan.getNamaPerusahaan();
                    String detail_pekerjaan = responsePekerjaan.getDetailPekerjaan();
                    String syarat_pekerjaan = responsePekerjaan.getSyaratPekerjaan();
                    String syarat_cv = responsePekerjaan.getSyaratCv();


                    Intent intent = new Intent(context,DetailPekerjaanInProfillePerusahaanActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("id_pekerjaan",id_data_jasa);
                    bundle.putString("nama_pekerjaan", namapekerjaan);
                    bundle.putString("gaji_min",gaji_min);
                    bundle.putString("gaji_max",gaji_max);
                    bundle.putString("nama_perusahaan",nama_perusahaan);
                    bundle.putString("detail_pekerjaan",detail_pekerjaan);
                    bundle.putString("syarat_pekerjaan",syarat_pekerjaan);
                    bundle.putString("syarat_cv",syarat_cv);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ResponsePekerjaan responsePekerjaan=(ResponsePekerjaan) itemView.getTag();
                    int id_data_jasa=responsePekerjaan.getId();
                    SharedPreferences sharedPref = itemView.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                    token = sharedPref.getString("user_token","");
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Really Delete")
                            .setMessage("Are you sure want to delete ?")
                            .setNegativeButton(android.R.string.no,null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(itemView.getContext(),"token : "+String.valueOf(id_data_jasa) +"token "+token,Toast.LENGTH_SHORT).show();
                                    service.delete_data_jasa(id_data_jasa,token)
                                            .enqueue(new Callback<Response>() {
                                                @Override
                                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                                    if (response.isSuccessful()){
//                                                        Intent intent = new Intent(itemView.getContext(),TryPerofilleActivity.class);
//                                                        itemView.getContext().startActivity(intent);
                                                        Toast.makeText(itemView.getContext(),"sukses ",Toast.LENGTH_SHORT).show();
                                                        responsePekerjaans.remove(responsePekerjaan);
                                                        notifyDataSetChanged();
                                                    }else {
                                                        Toast.makeText(itemView.getContext(),"something error ",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Response> call, Throwable t) {
                                                    Toast.makeText(itemView.getContext(),"error "+t,Toast.LENGTH_SHORT).show();
                                                }
                                            });
//                                    service.up

                                }
                            }).create().show();
                }
            });

            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsePekerjaan responsePekerjaan = (ResponsePekerjaan) itemView.getTag();
                    int id_data_jasa = responsePekerjaan.getId();
                    String namapekerjaan = responsePekerjaan.getPekerjaan();
                    String gaji_min = String.valueOf(responsePekerjaan.getGajiMin());
                    String gaji_max = String.valueOf(responsePekerjaan.getGajiMax());
                    String nama_perusahaan = responsePekerjaan.getNamaPerusahaan();
                    String detail_pekerjaan = responsePekerjaan.getDetailPekerjaan();
                    String syarat_pekerjaan = responsePekerjaan.getSyaratPekerjaan();
                    String syarat_cv = responsePekerjaan.getSyaratCv();

                    Intent intent = new Intent(itemView.getContext(),EditJobByUserActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("id_pekerjaan",id_data_jasa);
                    bundle.putString("nama_pekerjaan", namapekerjaan);
                    bundle.putString("gaji_min",gaji_min);
                    bundle.putString("gaji_max",gaji_max);
                    bundle.putString("nama_perusahaan",nama_perusahaan);
                    bundle.putString("detail_pekerjaan",detail_pekerjaan);
                    bundle.putString("syarat_pekerjaan",syarat_pekerjaan);
                    bundle.putString("syarat_cv",syarat_cv);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }


    public ListDataJasaInUserAdapter(Context context,List<ResponsePekerjaan> responsePekerjaans){
        this.responsePekerjaans=responsePekerjaans;
        this.context=context;
    }

    @NonNull
    @Override
    public ListDataJasaInUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data_jasa_in_profilleuser,parent,false);
        return new ListDataJasaInUserAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(responsePekerjaans.get(position));

        ResponsePekerjaan responsePekerjaan = responsePekerjaans.get(position);
        holder.textView_pekerjaan.setText(responsePekerjaan.getPekerjaan());
        holder.tv_gaji_min.setText(String.valueOf(responsePekerjaan.getGajiMin()));
        holder.tv_gaji_max.setText(String.valueOf(responsePekerjaan.getGajiMax()));
    }

    @Override
    public int getItemCount(){
        return responsePekerjaans.size();
    }

}
