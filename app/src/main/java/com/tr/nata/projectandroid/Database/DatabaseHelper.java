package com.tr.nata.projectandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.tr.nata.projectandroid.model.DataJasaItem;
import com.tr.nata.projectandroid.model.DataKategoriItem;
import com.tr.nata.projectandroid.model.DataUser;
import com.tr.nata.projectandroid.model.DataUserItem;
import com.tr.nata.projectandroid.model.ResponseDataJasaUser;
import com.tr.nata.projectandroid.model.ResponseFavorite;
import com.tr.nata.projectandroid.model.ResponsePekerjaan;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sidejob.db";

    //Tabel Kategori
    public static final String TABLE_NAME_KATEGORI = "category_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_KATEGORI = "kategori";

    //Tabel Data Jasa
//    public static final String TABLE_NAME_JASA = "data_jasa_table";
//    public static final String COLUMN_ID_KATEGORI = "id_kategori";
//    public static final String COLUMN_ID_USER = "id_user";
//    public static final String COLUMN_PEKERJAAN = "pekerjaan";
//    public static final String COLUMN_ESTIMASI_GAJI="estimasi_gaji";
//    public static final String COLUMN_USIA="usia";
//    public static final String COLUMN_NO_TELP="no_telp";
//    public static final String COLUMN_EMAIL_JASA="email_jasa";
//    public static final String COLUMN_STATUS="status";
//    public static final String COLUMN_STATUS_VALIDASI="status_validasi";
//    public static final String COLUMN_ALAMAT_JASA="alamat";
//    public static final String COLUMN_PENGALAMAN_KERJA="pengalaman_kerja";

    //Tabel Data User
    public static final String TABLE_NAME_USER = "data_user_table";
    public static final String COLUMN_NAME_USER = "name";
    public static final String COLUMN_EMAIL_USER="email";
    public static final String COLUMN_JK_USER = "jenis_kelamin";
    public static final String COLUMN_NO_TELP_USER="no_telp";
    public static final String COLUMN_TANGGAL_LAHIR_USER = "tanggal_lahir";

    //Tabel Favorite
    public static final String TABLE_NAME_FAVORITE = "data_favorite_table";
    public static final String COLUMN_ID_USER_FAVORITE = "id_user";
    public static final String COLUMN_ID_DATA_JASA_FAVORITE = "id_data_jasa";


    //pembuatan database
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,16);
    }

    //pembuatan tabel
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_KATEGORI + "(id integer primary key autoincrement, kategori text);");

        db.execSQL("create table " + ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN + "(" +
                DataJasaItem.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID +" integer," +
                ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI+" integer," +
                ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN+" integer," +
                ResponsePekerjaan.Entry.COLUMN_NAMA_PERUSAHAAN+" text," +
                ResponsePekerjaan.Entry.COLUMN_EMAIL_PERUSAHAAN+" text," +
                ResponsePekerjaan.Entry.COLUMN_PEKERJAAN+" text,"+
                ResponsePekerjaan.Entry.COLUMN_GAJI_MIN+" integer," +
                ResponsePekerjaan.Entry.COLUMN_GAJI_MAX+" integer," +
                ResponsePekerjaan.Entry.COLUMN_DETAIL_PEKERJAAN+" text,"+
                ResponsePekerjaan.Entry.COLUMN_SYARAT_PEKERJAAN+" text,"+
                ResponsePekerjaan.Entry.COLUMN_SYARAT_CV+" text,"+
                ResponsePekerjaan.Entry.COLUMN_STATUS_VALIDASI+" text);");

        db.execSQL("create table "+TABLE_NAME_USER+"(" +
                DataUserItem.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID+" integer," +
                COLUMN_NAME_USER+" text," +
                COLUMN_EMAIL_USER+" text," +
                COLUMN_JK_USER+" text," +
                COLUMN_NO_TELP_USER+" text," +
                COLUMN_TANGGAL_LAHIR_USER+" text);");

        db.execSQL("create table "+TABLE_NAME_FAVORITE+"(" +
                ResponseFavorite.Entry._ID+" integer primary key autoincrement," +
                COLUMN_ID+" integer," +
                COLUMN_ID_USER_FAVORITE+" integer," +
                COLUMN_ID_DATA_JASA_FAVORITE+" integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KATEGORI);
        db.execSQL("DROP TABLE IF EXISTS " + ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAVORITE);
        onCreate(db);
    }

    //Tabel Kategori
    public boolean insertDataKategori(int id,String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_KATEGORI,kategori);
        long result = db.insert(TABLE_NAME_KATEGORI,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertDataKategori(String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_KATEGORI,kategori);
        long result = db.insert(TABLE_NAME_KATEGORI,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_KATEGORI,null);
        return res;
    }

    public List<DataKategoriItem> selectKategori(){
        List<DataKategoriItem> dataKategoriItems = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlquery = "select * from "+ TABLE_NAME_KATEGORI;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
//        Cursor cursor=sqLiteDatabase.query(DataKategoriItem.Entry.TABLE_NAME_KATEGORI,null,null,null,null, null,null);

        int count = cursor.getCount();

//        if (count>0){
////            cursor.moveToFirst();
//            while (cursor.moveToNext()){
//                int id = cursor.getInt(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_ID));
//                String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));
//
//                DataKategoriItem temp = new DataKategoriItem();
//                temp.setId(id);
//                temp.setKategori(kategori);
//                dataKategoriItems.add(temp);
//            }
//
//        }

        //cara dari pak agung cahyawan
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_ID));
                String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));

                DataKategoriItem temp = new DataKategoriItem();
                temp.setId(id);
                temp.setKategori(kategori);
                dataKategoriItems.add(temp);
            }while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();
        return dataKategoriItems;
    }

    public boolean updateData(String id, String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_KATEGORI,kategori);

        db.update(TABLE_NAME_KATEGORI,contentValues,"ID = ?",new String[]{id});
        return true;
    }

    public void deleteKategori(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataKategoriItem.Entry.TABLE_NAME_KATEGORI,null,null);
    }

    //Untuk Tabel Data Jasa
    public boolean insertPekerjaan(int id,int id_kategori,int id_perusahaan,String nama_perusahaan,
                                  String email_perusahaan,String pekerjaan,int gaji_min,int gaji_max,
                                   String detail_pekerjaan,String syarat_pekerjaan,String syarat_cv,
                                   String status_validasi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI,id_kategori);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN,id_perusahaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_NAMA_PERUSAHAAN,nama_perusahaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_EMAIL_PERUSAHAAN,email_perusahaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_PEKERJAAN,pekerjaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_GAJI_MIN,gaji_min);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_GAJI_MAX,gaji_max);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_DETAIL_PEKERJAAN,detail_pekerjaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_SYARAT_PEKERJAAN,syarat_pekerjaan);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_SYARAT_CV,syarat_cv);
        contentValues.put(ResponsePekerjaan.Entry.COLUMN_STATUS_VALIDASI,status_validasi);
//
        long result = db.insert(ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getAllDataPekerjaan(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN,null);
        return res;
    }

    public void deletePekerjaan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN,ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI+"="+id,null);
    }

    public void deletePekerjaaninPerusahaan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN,ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN+"="+id,null);
    }

    public void deletePekerjaanOne(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN,ResponsePekerjaan.Entry.COLUMN_ID+"="+id,null);
    }

    public List<ResponsePekerjaan> selectPekerjaaninUser(int id_perusahaan){
        List<ResponsePekerjaan> responsePekerjaans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN + " where " + ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN + " = " + id_perusahaan;

        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
        int count = cursor1.getCount();
        int id_data_jasa = 0;
        String pekerjaan= "a";
        if (count>0){
            cursor1.moveToFirst();

            do{
//                id_data_jasa = cursor1.getInt(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
                int id_perusahaan_user = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN));
                int id_kategori = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI));
                String nama_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_NAMA_PERUSAHAAN));
                String email_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_EMAIL_PERUSAHAAN));

                pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_PEKERJAAN));
                int gaji_min = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MIN));
                int gaji_max = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MAX));

                String detail_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_DETAIL_PEKERJAAN));

                String syarat_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_PEKERJAAN));
                String syarat_cv = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_CV));

                String status_validasi = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_STATUS_VALIDASI));

                ResponsePekerjaan temp = new ResponsePekerjaan();
                temp.setIdKategori(id_kategori);
                temp.setIdPerusahaan(id_perusahaan_user);
                temp.setNamaPerusahaan(nama_perusahaan);
                temp.setEmailPerusahaan(email_perusahaan);
                temp.setPekerjaan(pekerjaan);
                temp.setGajiMin(gaji_min);
                temp.setGajiMax(gaji_max);
                temp.setDetailPekerjaan(detail_pekerjaan);
                temp.setSyaratPekerjaan(syarat_pekerjaan);
                temp.setSyaratCv(syarat_cv);
                temp.setStatusValidasi(status_validasi);

                responsePekerjaans.add(temp);
            }while (cursor1.moveToNext());
        }

        cursor1.close();
        sqLiteDatabase.close();
        return responsePekerjaans;
    }

    public List<ResponsePekerjaan> selectPekerjaan(int id_kategori){
        List<ResponsePekerjaan> responsePekerjaans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN + " where " + ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI + " = " + id_kategori;
//        Cursor c = db.rawQuery(sqlQuery,null);
//        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
        int count = cursor1.getCount();
        int id_data_jasa = 0;
        String pekerjaan= "a";
        if (count>0){
            cursor1.moveToFirst();

            do{
                int id_perusahaan_user = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN));
                int id_kategori_user = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI));
                String nama_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_NAMA_PERUSAHAAN));
                String email_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_EMAIL_PERUSAHAAN));

                pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_PEKERJAAN));
                int gaji_min = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MIN));
                int gaji_max = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MAX));

                String detail_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_DETAIL_PEKERJAAN));

                String syarat_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_PEKERJAAN));
                String syarat_cv = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_CV));

                String status_validasi = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_STATUS_VALIDASI));

                ResponsePekerjaan temp = new ResponsePekerjaan();
                temp.setIdKategori(id_kategori_user);
                temp.setIdPerusahaan(id_perusahaan_user);
                temp.setNamaPerusahaan(nama_perusahaan);
                temp.setEmailPerusahaan(email_perusahaan);
                temp.setPekerjaan(pekerjaan);
                temp.setGajiMin(gaji_min);
                temp.setGajiMax(gaji_max);
                temp.setDetailPekerjaan(detail_pekerjaan);
                temp.setSyaratPekerjaan(syarat_pekerjaan);
                temp.setSyaratCv(syarat_cv);
                temp.setStatusValidasi(status_validasi);

                responsePekerjaans.add(temp);
            }while (cursor1.moveToNext());
        }
//        if (count>0){
//            cursor.moveToFirst();
//            while (cursor.moveToNext()){
//                int id_data_jasa = cursor.getInt(cursor.getColumnIndex(DataJasaItem.Entry.COLUMN_ID));
//                String pekerjaan = cursor.getString(cursor.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
//
//                DataJasaItem temp = new DataJasaItem();
//                temp.setId(id_data_jasa);
//                temp.setPekerjaan(pekerjaan);
//                dataJasaItems.add(temp);
//            }
//
//        }
        cursor1.close();
        sqLiteDatabase.close();
        return responsePekerjaans;
    }

    public ResponsePekerjaan selectOneDatajasa(int id_pekerjaan){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + ResponsePekerjaan.Entry.TABLE_NAME_PEKERJAAN + " where " + ResponsePekerjaan.Entry.COLUMN_ID + " = " + id_pekerjaan;
//        Cursor c = db.rawQuery(sqlQuery,null);
//        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);

        cursor1.moveToFirst();
        int id_perusahaan_user = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_PERUSAHAAN));
        int id_kategori_user = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_ID_KATEGORI));
        String nama_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_NAMA_PERUSAHAAN));
        String email_perusahaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_EMAIL_PERUSAHAAN));

        String pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_PEKERJAAN));
        int gaji_min = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MIN));
        int gaji_max = cursor1.getInt(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_GAJI_MAX));

        String detail_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_DETAIL_PEKERJAAN));

        String syarat_pekerjaan = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_PEKERJAAN));
        String syarat_cv = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_SYARAT_CV));

        String status_validasi = cursor1.getString(cursor1.getColumnIndex(ResponsePekerjaan.Entry.COLUMN_STATUS_VALIDASI));

        ResponsePekerjaan temp = new ResponsePekerjaan();
        temp.setIdKategori(id_kategori_user);
        temp.setIdPerusahaan(id_perusahaan_user);
        temp.setNamaPerusahaan(nama_perusahaan);
        temp.setEmailPerusahaan(email_perusahaan);
        temp.setPekerjaan(pekerjaan);
        temp.setGajiMin(gaji_min);
        temp.setGajiMax(gaji_max);
        temp.setDetailPekerjaan(detail_pekerjaan);
        temp.setSyaratPekerjaan(syarat_pekerjaan);
        temp.setSyaratCv(syarat_cv);
        temp.setStatusValidasi(status_validasi);

        cursor1.close();
        sqLiteDatabase.close();
        return temp;
    }

//    public String jumlah_data_jasa(int id_kategori){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String sqlQuery1="select * from " + TABLE_NAME_JASA + " where " + COLUMN_ID_KATEGORI + " = " + id_kategori;
////        Cursor c = db.rawQuery(sqlQuery,null);
////        Cursor cursor=sqLiteDatabase.query(DataJasaItem.Entry.TABLE_NAME_JASA,null,COLUMN_ID_KATEGORI+"="+id,null ,null, null,null);
//        Cursor cursor1=sqLiteDatabase.rawQuery(sqlQuery1,null);
//        int count = cursor1.getCount();
//        String data_jasa="a";
//        if (count>0) {
//            cursor1.moveToFirst();
//             data_jasa= cursor1.getString(cursor1.getColumnIndex(DataJasaItem.Entry.COLUMN_PEKERJAAN));
//        }
////        String kategori = cursor.getString(cursor.getColumnIndex(DataKategoriItem.Entry.COLUMN_KATEGORI));
//        cursor1.close();
//        sqLiteDatabase.close();
//        return data_jasa;
//    }

    //Untuk Tabel Data User
    public boolean insertDataUser(int id,String name,String email,String jenis_kelamin, String no_telp,String tanggal_lahir){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NAME_USER,name);
        contentValues.put(COLUMN_EMAIL_USER,email);
        contentValues.put(COLUMN_JK_USER,jenis_kelamin);
        contentValues.put(COLUMN_NO_TELP_USER,no_telp);
        contentValues.put(COLUMN_TANGGAL_LAHIR_USER,tanggal_lahir);
        long result = db.insert(TABLE_NAME_USER,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getDataUser(int id_user){
//        DataUserItem dataUserItem=new DataUserItem();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_USER+" where "+COLUMN_ID+" = "+ id_user,null);

        return res;
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataUserItem.Entry.TABLE_NAME_USER,COLUMN_ID+"="+id,null);
    }

    public DataUserItem selectDataUser(int id_user){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor=sqLiteDatabase.query(DataUserItem.Entry.TABLE_NAME_USER,null,COLUMN_ID+"="+id_user,null ,null, null,null);

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ TABLE_NAME_USER+" where "+COLUMN_ID+" = "+ id_user,null);
        cursor.moveToFirst();
//      int id = cursor.getInt(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_ID));

        String name = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
        String email = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
        String jk = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
        String no_telp = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
        String tanggal_lahir = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));

        DataUserItem temp = new DataUserItem();
//        temp.setId(id);
        temp.setName(name);
        temp.setEmail(email);
        temp.setJenisKelamin(jk);
        temp.setNoTelp(no_telp);
        temp.setTanggalLahir(tanggal_lahir);

//        int count = cursor.getCount();
//        if (count>0){
//            cursor.moveToFirst();
//            do{
//                int id = cursor.getInt(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_ID));
//                String name = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
//                String email = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
//                String jk = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
//                String no_telp = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
//                String tanggal_lahir = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));
//
//                DataUserItem temp = new DataUserItem();
//                temp.setId(id);
//                temp.setName(name);
//                temp.setEmail(email);
//                temp.setJenisKelamin(jk);
//                temp.setNoTelp(no_telp);
//                temp.setTanggalLahir(tanggal_lahir);
//                dataUserItems.add(temp);
//            }while (cursor.moveToNext());
//
//        }
        cursor.close();
        sqLiteDatabase.close();
        return temp;
    }

    //Untuk Tabel Data Jasa
    public boolean insertDataFavorite(int id,int id_user,int id_data_jasa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_ID_USER_FAVORITE,id_user);
        contentValues.put(COLUMN_ID_DATA_JASA_FAVORITE,id_data_jasa);

        long result = db.insert(TABLE_NAME_FAVORITE,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public List<ResponseFavorite> getAllDataFavorite(){
        List<ResponseFavorite> responseFavorites = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor2 = db.rawQuery("select * from "+ TABLE_NAME_FAVORITE,null);

        int count = cursor2.getCount();
        if(count>0){
            cursor2.moveToFirst();
            do{
                int id_favorite = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID));
                int id_user = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID_USER_FAVORITE));
                int id_data_jasa = cursor2.getInt(cursor2.getColumnIndex(ResponseFavorite.Entry.COLUMN_ID_DATA_JASA_FAVORITE));

                ResponseFavorite temp = new ResponseFavorite();
                temp.setId(id_favorite);
                temp.setIdUser(id_user);
                temp.setIdDataJasa(id_data_jasa);
                responseFavorites.add(temp);

            }while (cursor2.moveToNext());
        }
        cursor2.close();
        db.close();
        return responseFavorites;
    }

    public void deleteFavorite(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ResponseFavorite.Entry.TABLE_NAME_FAVORITE,null,null);
    }

    public int checkFavorite(int id_user,int id_data_jasa){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sqlQuery1="select * from " + TABLE_NAME_FAVORITE + " where " + COLUMN_ID_USER_FAVORITE + " = " + id_user + " and " + COLUMN_ID_DATA_JASA_FAVORITE + " = " + id_data_jasa;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery1,null);
        int jumlah = cursor.getCount();

        return jumlah;
    }

}
