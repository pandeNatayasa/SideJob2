package com.tr.nata.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class profilleUserActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    ImageView img_logout;
    private TextView tv_nama_profille;
    ImageView img_change_foto_profille,img_fotoProfille;
    //    private ViewPager viewPager;
    String path;
    TextView tv_name, tv_email, tv_jenis_kelamin, tv_notelp, tv_tanggal_lahir;
    FloatingActionButton fab_edit_profiile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profille_user);

        img_change_foto_profille=findViewById(R.id.img_change_fotoprofille_user);
        img_fotoProfille=findViewById(R.id.img_foto_profille_inProfille_user);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid_profille_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout=findViewById(R.id.appbarLayout_id_user);
        img_logout=findViewById(R.id.img_logout_user);

        tv_nama_profille=findViewById(R.id.tv_nama_proflle_inProfille_user);
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        String nama_user_login = sharedPref.getString("nama_user_login","");
        String foto_profille = sharedPref.getString("user_foto_profille","");
        String url = "http://172.17.100.2:8000"+foto_profille;
        Glide.with(this).load(url).into(img_fotoProfille);
        tv_nama_profille.setText(nama_user_login);

        img_change_foto_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), UpdateFotoProfille.class);
//                startActivity(intent);
                selectImage();
            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        tv_name=findViewById(R.id.tv_name_profille_user);
        tv_email=findViewById(R.id.tv_email_profille_user);
        tv_jenis_kelamin=findViewById(R.id.tv_jk_profille_user);
        tv_notelp=findViewById(R.id.tv_notelp_profille_user);
        tv_tanggal_lahir=findViewById(R.id.tv_tanggal_lahir_profille_user);
        fab_edit_profiile=findViewById(R.id.fab_edit_profille_user);

//        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
//        String nama_user_login = sharedPref.getString("nama_user_login","");
        String email_user_login = sharedPref.getString("email_user_login","");
        String jk_user_login = sharedPref.getString("jk_user_login","");
        String no_telp_user_login = sharedPref.getString("no_telp_user_login","");
        String tanggal_lahir_user_login = sharedPref.getString("tanggal_lahir_user_login","");

        tv_name.setText(nama_user_login);
        tv_email.setText(email_user_login);
        tv_jenis_kelamin.setText(jk_user_login);
        tv_notelp.setText(no_telp_user_login);
        tv_tanggal_lahir.setText(tanggal_lahir_user_login);

        fab_edit_profiile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profilleUserActivity.this,"will be update",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(profilleUserActivity.this,EditProfilleActivity.class);
                startActivity(intent);
            }
        });

    }
    public void logout(){
        new AlertDialog.Builder(profilleUserActivity.this)
                .setTitle("Really Logout")
                .setMessage("Are you sure want to logout ?")
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sharedPref.edit().clear().commit();

//                        editor.putString("status_login_string", String.valueOf(response.body().isStatus()));
//                        editor.putInt("id_user_login",response.body().getDataUser().getId());
//                        editor.putString("nama_user_login", String.valueOf(response.body().getDataUser().getName()));
//                        editor.putString("email_user_login",String.valueOf(response.body().getDataUser().getEmail()));
//                        editor.putString("jk_user_login", String.valueOf(response.body().getDataUser().getJenisKelamin()));
//                        editor.putString("no_telp_user_login", String.valueOf(response.body().getDataUser().getNoTelp()));
//                        editor.putString("tanggal_lahir_user_login", String.valueOf(response.body().getDataUser().getTanggalLahir()));
//                        editor.putString("status_user",String.valueOf(response.body().getDataUser().getStatusUser()));

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Select picture, from album.
     */
    private void selectImage() {
        Album.image(this)
                .singleChoice()
                .camera(true)
                .widget(
                        Widget.newDarkBuilder(this)
                                .build()
                )
                .onResult((Action<ArrayList<AlbumFile>>) result -> {
                    path = result.get(0).getPath();
                    Toast.makeText(this,"path : "+path,Toast.LENGTH_SHORT).show();
                    String filename = path.substring(path.lastIndexOf("/")+1);
//                    et_logo_kategori.setText(filename);
//                    mAlbumFiles = result;
//                    Bundle bundle
                    Intent intent = new Intent(getApplicationContext(),UpdateFotoProfille.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("path", path);
                    bundle.putString("filename", filename);
                    intent.putExtras(bundle);
                    startActivity(intent);
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(profilleUserActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }
}
