package com.tr.nata.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPekerjaanInProfillePerusahaanActivity extends AppCompatActivity {

    TextView tv_nama_pekerjaan, tv_gaji_min,tv_gaji_max,tv_nama_perusahaan,
            tv_detail_pekerjaan,tv_syarat_pekerjaan,tv_syarat_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pekerjaan_in_profille_perusahaan);

        tv_nama_pekerjaan=findViewById(R.id.tv_pekerjaan_detail_inprofille);
        tv_gaji_min=findViewById(R.id.tv_gaji_min_inprofille);
        tv_gaji_max=findViewById(R.id.tv_gaji_max_inprofille);
        tv_nama_perusahaan=findViewById(R.id.tv_nama_perusahaan_detail_inprofille);
        tv_detail_pekerjaan=findViewById(R.id.tv_detail_pekerjaan_detail_inprofille);
        tv_syarat_pekerjaan=findViewById(R.id.tv_syarat_pekerjaan_detail_inprofille);
        tv_syarat_cv=findViewById(R.id.tv_syarat_cv_detail_inprofille);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(DetailPekerjaanInProfillePerusahaanActivity.this,"nama pekerjaan :"+bundle.getString("nama_pekerjaan"),Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailPekerjaanInProfillePerusahaanActivity.this,"nama perusahaan :"+bundle.getString("nama_perusahaan"),Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailPekerjaanInProfillePerusahaanActivity.this,"detail pekerjaan :"+bundle.getString("detail_pekerjaan"),Toast.LENGTH_SHORT).show();

        tv_nama_pekerjaan.setText(bundle.getString("nama_pekerjaan"));
        tv_gaji_min.setText(bundle.getString("gaji_min"));
        tv_gaji_max.setText(bundle.getString("gaji_max"));
        tv_nama_perusahaan.setText(bundle.getString("nama_perusahaan"));
        tv_detail_pekerjaan.setText(bundle.getString("detail_pekerjaan"));
        tv_syarat_pekerjaan.setText(bundle.getString("syarat_pekerjaan"));
        tv_syarat_cv.setText(bundle.getString("syarat_cv"));

    }
}
