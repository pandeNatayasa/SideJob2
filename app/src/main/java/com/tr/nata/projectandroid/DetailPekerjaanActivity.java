package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPekerjaanActivity extends AppCompatActivity {
    TextView tv_nama_pekerjaan, tv_gaji_min,tv_gaji_max,tv_nama_perusahaan,
            tv_detail_pekerjaan,tv_syarat_pekerjaan;
    Button btn_SendCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pekerjaan);

        tv_nama_pekerjaan=findViewById(R.id.tv_pekerjaan_detail);
        tv_gaji_min=findViewById(R.id.tv_gaji_min);
        tv_gaji_max=findViewById(R.id.tv_gaji_max);
        tv_nama_perusahaan=findViewById(R.id.tv_nama_perusahaan_detail);
        tv_detail_pekerjaan=findViewById(R.id.tv_detail_pekerjaan_detail);
        tv_syarat_pekerjaan=findViewById(R.id.tv_syarat_pekerjaan_detail);
        btn_SendCV=findViewById(R.id.btn_sendCV);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(DetailPekerjaanActivity.this,"nama pekerjaan :"+bundle.getString("nama_pekerjaan"),Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailPekerjaanActivity.this,"nama perusahaan :"+bundle.getString("nama_perusahaan"),Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailPekerjaanActivity.this,"detail pekerjaan :"+bundle.getString("detail_pekerjaan"),Toast.LENGTH_SHORT).show();

        tv_nama_pekerjaan.setText(bundle.getString("nama_pekerjaan"));
        tv_gaji_min.setText(bundle.getString("gaji_min"));
        tv_gaji_max.setText(bundle.getString("gaji_max"));
        tv_nama_perusahaan.setText(bundle.getString("nama_perusahaan"));
        tv_detail_pekerjaan.setText(bundle.getString("detail_pekerjaan"));
        tv_syarat_pekerjaan.setText(bundle.getString("syarat_pekerjaan"));
        String emailPerusahaan = bundle.getString("email_perusahaan");
        String syarat_CV = bundle.getString("syarat_cv");


        btn_SendCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailPekerjaanActivity.this)
                        .setTitle("Send CV")
                        .setMessage("Syarat CV : \n"+syarat_CV+"\n\nSilahkan kirim cv ke alamat email : "+emailPerusahaan)
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });

    }
}
