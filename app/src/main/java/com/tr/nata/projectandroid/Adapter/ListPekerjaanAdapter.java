package com.tr.nata.projectandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.DetailPekerjaanActivity;
import com.tr.nata.projectandroid.DetailUserActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponsePekerjaan;

import java.util.List;

public class ListPekerjaanAdapter extends RecyclerView.Adapter<ListPekerjaanAdapter.ViewHolder> {

    private List<ResponsePekerjaan> responsePekerjaans;
    private Context context;
    private CardView listDataPekerjaan;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_perusahaan,gaji_min,gaji_max, pekerjaan;

        public ViewHolder(View itemView) {
            super(itemView);

            context=itemView.getContext();

            nama_perusahaan=itemView.findViewById(R.id.tv_nama_perusahaan);
            gaji_min=itemView.findViewById(R.id.tv_gaji_min);
            gaji_max=itemView.findViewById(R.id.tv_gaji_max);
            pekerjaan=itemView.findViewById(R.id.tv_pekerjaan);
            listDataPekerjaan=itemView.findViewById(R.id.cardView_list_pekerjaan);
        }
    }
    public ListPekerjaanAdapter(List<ResponsePekerjaan> responsePekerjaans, Context context){
        this.responsePekerjaans=responsePekerjaans;
        this.context=context;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycler_list_user_layout,parent,false);
//
//        return new ViewHolder(v);
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group,int viewType){
        View v = LayoutInflater.from(group.getContext())
                .inflate(R.layout.recycler_list_user_layout,group,false);
        return new ListPekerjaanAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ListPekerjaanAdapter.ViewHolder holder, int position) {

//        holder.itemView.setTag(dataUserItems.get(position));
//        holder.itemView.setTag(dataJasaItems.get(position));
        holder.itemView.setTag(responsePekerjaans.get(position));

//        DataJasaItem dataJasa = dataJasaItems.get(position);
//        DataUserItem dataUser = dataUserItems.get(position);
        ResponsePekerjaan responsePekerjaan = responsePekerjaans.get(position);
        Log.d("position","ke-"+position);
        holder.pekerjaan.setText(responsePekerjaan.getPekerjaan());
        holder.nama_perusahaan.setText(responsePekerjaan.getNamaPerusahaan());
        holder.gaji_min.setText(String.valueOf(responsePekerjaan.getGajiMin()));
        holder.gaji_max.setText(String.valueOf(responsePekerjaan.getGajiMax()));
////        holder. .setText(responsePekerjaan.getNamaPerusahaan());

        listDataPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                DataJasaItem dataJasaItem = (DataJasaItem) itemView.getTag();
//                DataUserItem dataUserItem = (DataUserItem) itemView.getTag();

                int id_data_jasa = responsePekerjaan.getId();
                String namapekerjaan = responsePekerjaan.getPekerjaan();
                String gaji_min = String.valueOf(responsePekerjaan.getGajiMin());
                String gaji_max = String.valueOf(responsePekerjaan.getGajiMax());
                String nama_perusahaan = responsePekerjaan.getNamaPerusahaan();
                String detail_pekerjaan = responsePekerjaan.getDetailPekerjaan();
                String syarat_pekerjaan = responsePekerjaan.getSyaratPekerjaan();
                String emailPerusahaan = responsePekerjaan.getEmailPerusahaan();
                String syaratCV = responsePekerjaan.getSyaratCv();

                Toast.makeText(view.getContext(),"syarat cv "+syaratCV,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,DetailPekerjaanActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("id_pekerjaan",id_data_jasa);
                bundle.putString("nama_pekerjaan", namapekerjaan);
                bundle.putString("gaji_min",gaji_min);
                bundle.putString("gaji_max",gaji_max);
                bundle.putString("nama_perusahaan",nama_perusahaan);
                bundle.putString("email_perusahaan",emailPerusahaan);
                bundle.putString("detail_pekerjaan",detail_pekerjaan);
                bundle.putString("syarat_pekerjaan",syarat_pekerjaan);
                bundle.putString("syarat_cv",syaratCV);

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount(){
        return responsePekerjaans.size();
    }
}
