package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EditJobByUserActivity extends AppCompatActivity {

    TextView tv_error;
    ImageView img_add_to_profille;

    EditText et_pekerjaan,et_gaji_min,et_gaji_max,et_detail_pekerjaan,
            et_syarat_pekerjaan,et_syarat_cv;

    ApiService service;
    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();
    Spinner sp_kategori,sp_status_new;
    int id_kategori;
    String status_new,user_token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_job_by_user);

//        tv_error=view.findViewById(R.id.tv_error_here);
        img_add_to_profille=findViewById(R.id.img_new_job_to_profille);

        service=ApiClient.getApiService();

        Button btn_new_job=findViewById(R.id.btn_save_new_job);
//        et_id_kategori=view.findViewById(R.id.et_kategori_new);
        sp_kategori=findViewById(R.id.sp_kategori);
        et_pekerjaan=findViewById(R.id.et_pekerjaan_new);
        et_gaji_min=findViewById(R.id.et_estimasi_gaji_min_new);
        et_gaji_max=findViewById(R.id.et_estimasi_gaji_max_new);
        et_detail_pekerjaan=findViewById(R.id.et_detail_pekerjaan_new);
        et_syarat_pekerjaan=findViewById(R.id.et_syarat_pekerjaan_new);
        et_syarat_cv=findViewById(R.id.et_syarat_cv_new);

        Bundle bundle = getIntent().getExtras();
        int id_pekerjaan = bundle.getInt("id_pekerjaan",0);
        String pekerjaan = bundle.getString("nama_pekerjaan", "");
        String gaji_min = bundle.getString("gaji_min","");
        String gaji_max = bundle.getString("gaji_max","");
        String nama_perusahaan = bundle.getString("nama_perusahaan","");
        String detail_pekerjaan =  bundle.getString("detail_pekerjaan","");
        String syarat_pekerjaan = bundle.getString("syarat_pekerjaan","");
        String syarat_cv = bundle.getString("syarat_cv","");

        //membersihkan edit text
        et_pekerjaan.setText(pekerjaan);
        et_gaji_min.setText(gaji_min);
        et_gaji_max.setText(gaji_max);
        et_detail_pekerjaan.setText(detail_pekerjaan);
        et_syarat_pekerjaan.setText(syarat_pekerjaan);
        et_syarat_cv.setText(syarat_cv);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dataKategoriItems=dbHelper.selectKategori();
        List<String> list = new ArrayList<>();
        for (int i=0;i<dataKategoriItems.size();i++){
            list.add(dataKategoriItems.get(i).getKategori());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_kategori.setAdapter(adapter);

        SharedPreferences sharedPref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

//        sp_status_new.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                status_new = adapterView.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String nama_kategori = adapterView.getItemAtPosition(position).toString();
                for (int i=0;i<dataKategoriItems.size();i++){
                    if (nama_kategori.equals(dataKategoriItems.get(i).getKategori())){
                        id_kategori=dataKategoriItems.get(i).getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_new_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiAddNew(id_kategori);
            }
        });


        img_add_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditJobByUserActivity.this,TryPerofilleActivity.class);
                intent.putExtra("Fragment_id",0);
                startActivity(intent);
//                getActivity().finish();
            }
        });


    }

    private void callApiAddNew(int id_kategori){
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);

        int id_user = sharedPref.getInt("id_user_login",0);
        String nama_perusahaan = sharedPref.getString("nama_user_login","");
//        int id_kategori =this.id_kategori;
//                Integer.parseInt(String.valueOf(et_id_kategori.getText()));

        String pekerjaan = et_pekerjaan.getText().toString();
        int gaji_min = Integer.parseInt(String.valueOf(et_gaji_min.getText()));
        int gaji_max = Integer.parseInt(String.valueOf(et_gaji_max.getText()));
        String detail_pekerjaan = et_detail_pekerjaan.getText().toString();
        String syarat_pekerjaan = et_syarat_pekerjaan.getText().toString();
        String syarat_cv = et_syarat_cv.getText().toString();

//        Toast.makeText(AddJobActivity.this,"Haiiii",Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id_kategori "+id_kategori,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id_user"+id_user,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"pekerjaan :"+pekerjaan,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"estimasi gaji :"+estimasi_gaji,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"pengalaman kerja :"+pengalaman_kerja,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"no telp :"+no_telp,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"email :"+email,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"status :"+status,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"alamat :"+alamat,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"id kecamatan :"+id_kecamatan,Toast.LENGTH_SHORT).show();
//        Toast.makeText(AddJobActivity.this,"usia :"+usia,Toast.LENGTH_SHORT).show();

        service.newDataJasaUser(id_kategori,id_user,nama_perusahaan,pekerjaan,gaji_min,gaji_max,detail_pekerjaan,syarat_pekerjaan,syarat_cv,user_token)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<com.tr.nata.projectandroid.model.Response> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Jasa Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditJobByUserActivity.this,TryPerofilleActivity.class);
                            intent.putExtra("Fragment_id",0);
                            startActivity(intent);
//                            Intent intent = new Intent(AddJobActivity.this,HomeActivity.class);
//                            startActivity(intent);
//                            Fragment fragment = new FragmentListFrelance();
//                            getSupportFragmentManager().beginTransaction().add(R.id.frag_layout,fragment).addToBackStack(FragmentListFrelance.class.getSimpleName()).commit();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Jasa Gagal Disimpan",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.tr.nata.projectandroid.model.Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error : "+t,Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
