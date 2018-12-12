package com.tr.nata.projectandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tr.nata.projectandroid.Adapter.kategoriAdapter;
import com.tr.nata.projectandroid.Database.DatabaseHelper;
import com.tr.nata.projectandroid.HomeActivity;
import com.tr.nata.projectandroid.MainActivity;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.TryPerofilleActivity;
import com.tr.nata.projectandroid.api.ApiClient;
import com.tr.nata.projectandroid.api.ApiService;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.ResponseKategori;
import com.tr.nata.projectandroid.profilleUserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    TextView tv_namaUser,tv_home_to_favorite;

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private kategoriAdapter adapter;
    DatabaseHelper myDb;
    ImageView home_to_profille,img_home_to_favorite;
    private List<ResponseKategori> responseKategoris = new ArrayList<>();

    private List<DataKategoriItem> dataKategoriItems = new ArrayList<>();

    ApiService service;
    String user_token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

//        tv_namaUser = view.findViewById(R.id.tv_nama);
        home_to_profille=view.findViewById(R.id.img_profille_home_to_profille);
        toolbar=view.findViewById(R.id.toolbarid);
        tv_home_to_favorite=view.findViewById(R.id.tv_home_to_favorite);
        img_home_to_favorite=view.findViewById(R.id.img_home_to_favorite);

        service=ApiClient.getApiService();
        myDb=new DatabaseHelper(getActivity());

        SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        user_token = sharedPref.getString("user_token","");

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_kategori);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),2,GridLayoutManager.VERTICAL,false));

        home_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),profilleUserActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });

        img_home_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentFavorite ff = new FragmentFavorite();

                ft.replace(R.id.frag_layout,ff);
                ft.commit();
            }
        });

        tv_home_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentFavorite ff = new FragmentFavorite();

                ft.replace(R.id.frag_layout,ff);
                ft.commit();
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_home_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorPrimaryLight);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        callKategoriLokal();
                        callApi();
                    }
                },3000);
            }
        });

        if (isConnected()){
            callKategoriLokal();
            callApi();
        }else {
            Toast.makeText(getActivity().getApplicationContext(),"Anda Sedang Ofline",Toast.LENGTH_SHORT).show();
            callKategoriLokal();
        }

        return view;
    }


    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean status = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return status;
    }

    private void callApi(){
        service.getKategori(user_token)
                .enqueue(new Callback<ResponseKategori>() {
                    @Override
                    public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                        if (response.isSuccessful()){
                            DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

                            dbHelper.deleteKategori();

                            dataKategoriItems=response.body().getDataKategori();

                            for (DataKategoriItem dataKategoriItem:dataKategoriItems){
                                dbHelper.insertDataKategori(dataKategoriItem.getId(),dataKategoriItem.getKategori());
                            }
                            setAdapter();
//                            recyclerView.notifyAll();

//                            responseKategoris.addAll((Collection<? extends ResponseKategori>) response.body());

//                            adapter=new kategoriAdapter(responseKategoris,this);
//                            recyclerView.setAdapter(adapter);
//                            dataKategoriItems = response.body().getDataKategori();
//                            adapter = new kategoriAdapter(dataKategoriItems,this);
//                            recyclerView.setAdapter(adapter);
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"login gagal",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKategori> call, Throwable t) {
//                        Toast.makeText(getActivity().getApplicationContext(),"Anda Sedang Ofline",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setAdapter(){
        adapter = new kategoriAdapter(getActivity(),dataKategoriItems);
        recyclerView.setAdapter(adapter);
    }

    private void callKategoriLokal(){
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        dataKategoriItems=dbHelper.selectKategori();
        setAdapter();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
