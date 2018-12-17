package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.tr.nata.projectandroid.Adapter.ListPekerjaanAdapter;
//import com.tr.nata.projectandroid.Adapter.listUserAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasa;
import com.tr.nata.projectandroid.model.ResponsePekerjaan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubHomeActivity extends AppCompatActivity {

    public TextView tv_pesan;
    private RecyclerView recyclerView;
//    private listUserAdapter listUserAdapter;
    private ListPekerjaanAdapter listPekerjaanAdapter;
    private Bundle bundle;
    private Button btn_view_jasa;
    ImageView img_subHome_to_profille;
    String user_token;

    private List<DataJasaItem> dataJasaItems = new ArrayList<>();
    private List<DataUserItem> dataUserItems=new ArrayList<>();
    private List<ResponsePekerjaan> responsePekerjaans = new ArrayList<>();

    ApiService service;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_home);

        tv_pesan=(TextView)findViewById(R.id.tv_pesan);
//        btn_view_jasa=findViewById(R.id.btn_data_jasa);
        img_subHome_to_profille=findViewById(R.id.img_sub_home_to_profille);

        bundle = getIntent().getExtras();
        String nama_kategori = bundle.getString("namaKategori");
        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(this);
//        ViewCompat.setTransitionName(findViewById(R.id.app_bar_sub_home),);
        CollapsingToolbarLayout  collapsingToolbarLayout= (CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar_subhome);
        collapsingToolbarLayout.setTitle(nama_kategori);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_sub_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_list_user);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

//        btn_view_jasa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = myDb.getAllDataJasa();
//                if (res.getCount()==0){
//                    //show message
//                    showMessage("Eror","Nothing Found");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Nama : "+res.getString(4)+"\n");
//                    buffer.append("Pekerjaan : "+res.getString(5)+"\n");
//                }
//                //show all data
//                showMessage("Data",buffer.toString());
//            }
//        });

        img_subHome_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),profilleUserActivity.class);
                intent.putExtra("Fragment_id",0);
                startActivity(intent);
            }
        });

        callApi();
        callDataLokal();
//        callJumlahDataJasaLokal();

    }

    private void callApi(){
        int id_kategori = bundle.getInt("id_kategori");
        service.showDataJasaByKategori(id_kategori,user_token)
                .enqueue(new Callback<List<ResponsePekerjaan>>() {
                    @Override
                    public void onResponse(Call<List<ResponsePekerjaan>> call, Response<List<ResponsePekerjaan>> response) {
                        if (response.isSuccessful()) {

//                            Toast.makeText(getApplicationContext(), "success beb", Toast.LENGTH_SHORT).show();
                            if (response.body().size() > 0) {
//                                Toast.makeText(getApplicationContext(), "jumlah data jasa " + response.body().getDataJasa().size(), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "jumlah user " + response.body().getDataUser().size(), Toast.LENGTH_SHORT).show();

                                myDb.deletePekerjaan(id_kategori);
                                responsePekerjaans=response.body();
//                                dataJasaItems = response.body().getDataJasa();
//                                dataUserItems = response.body().getDataUser();
//
//                                for (ResponsePekerjaan responsePekerjaan:responsePekerjaans){
//                                    myDb.deletePekerjaan(responsePekerjaan.getId());
//                                }
//                                for (DataUserItem dataUserItem:dataUserItems){
//                                    myDb.insertDataUser(dataUserItem.getId(),dataUserItem.getName(),dataUserItem.getEmail(),dataUserItem.getJenisKelamin(),
//                                            dataUserItem.getNoTelp(),dataUserItem.getTanggalLahir());
//
//                                }
////                                Toast.makeText(getApplicationContext(), "pengalaman kerja : " + dataJasaItems.get(0).getPengalaman_kerja(), Toast.LENGTH_SHORT).show();
                                for (ResponsePekerjaan responsePekerjaan:responsePekerjaans){
                                    boolean hasil = myDb.insertPekerjaan(responsePekerjaan.getId(),responsePekerjaan.getIdKategori(),responsePekerjaan.getIdPerusahaan(),responsePekerjaan.getNamaPerusahaan(),
                                            responsePekerjaan.getEmailPerusahaan(),responsePekerjaan.getPekerjaan(),responsePekerjaan.getGajiMin(),responsePekerjaan.getGajiMax(),responsePekerjaan.getDetailPekerjaan(),
                                            responsePekerjaan.getSyaratPekerjaan(),responsePekerjaan.getSyaratCv(),responsePekerjaan.getStatusValidasi());
                                    Toast.makeText(getApplicationContext(), "hasil " + hasil, Toast.LENGTH_SHORT).show();
                                }
                                setAdapter();
                            } else {
                                tv_pesan.setText("Data kosong");
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ResponsePekerjaan>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Anda Sedang Offline",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setAdapter(){
//        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
//        listUserAdapter = new listUserAdapter(dataJasaItems,dataUserItems,this);
//        recyclerView.setAdapter(listUserAdapter);
        listPekerjaanAdapter  = new ListPekerjaanAdapter(responsePekerjaans,this);
        recyclerView.setAdapter(listPekerjaanAdapter);

    }
    private void callJumlahDataJasaLokal(){
        int id_kategori = bundle.getInt("id_kategori");
//        String jumlah  = myDb.jumlah_data_jasa(id_kategori);
//        Toast.makeText(getApplicationContext(),"jumlah data jasa di SQLite:"+String.valueOf( jumlah),Toast.LENGTH_SHORT).show();
    }

    private void callDataLokal(){
        int id_kategori = bundle.getInt("id_kategori");
        responsePekerjaans=myDb.selectPekerjaan(id_kategori);
        setAdapter();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
