package com.tr.nata.projectandroid.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

public class ResponsePekerjaan{

	@SerializedName("id_kategori")
	private int idKategori;

	@SerializedName("gaji_min")
	private int gajiMin;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("nama_perusahaan")
	private String namaPerusahaan;

	@SerializedName("email_perusahaan")
	private String emailPerusahaan;

	@SerializedName("status_validasi")
	private String statusValidasi;

	@SerializedName("syarat_pekerjaan")
	private String syaratPekerjaan;

	@SerializedName("detail pekerjaan")
	private String detailPekerjaan;

	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("id_perusahaan")
	private int idPerusahaan;

	@SerializedName("gaji_max")
	private int gajiMax;

	@SerializedName("syarat_cv")
	private String syaratCv;

	@SerializedName("id")
	private int id;

	public void setIdKategori(int idKategori){
		this.idKategori = idKategori;
	}

	public int getIdKategori(){
		return idKategori;
	}

	public void setGajiMin(int gajiMin){
		this.gajiMin = gajiMin;
	}

	public int getGajiMin(){
		return gajiMin;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setNamaPerusahaan(String namaPerusahaan){
		this.namaPerusahaan = namaPerusahaan;
	}

	public String getNamaPerusahaan(){
		return namaPerusahaan;
	}

	public void setEmailPerusahaan(String emailPerusahaan){
		this.emailPerusahaan = emailPerusahaan;
	}

	public String getEmailPerusahaan(){
		return emailPerusahaan;
	}

	public void setStatusValidasi(String statusValidasi){
		this.statusValidasi = statusValidasi;
	}

	public String getStatusValidasi(){
		return statusValidasi;
	}

	public void setSyaratPekerjaan(String syaratPekerjaan){
		this.syaratPekerjaan = syaratPekerjaan;
	}

	public String getSyaratPekerjaan(){
		return syaratPekerjaan;
	}

	public void setDetailPekerjaan(String detailPekerjaan){
		this.detailPekerjaan = detailPekerjaan;
	}

	public String getDetailPekerjaan(){
		return detailPekerjaan;
	}

	public void setPekerjaan(String pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan(){
		return pekerjaan;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setIdPerusahaan(int idPerusahaan){
		this.idPerusahaan = idPerusahaan;
	}

	public int getIdPerusahaan(){
		return idPerusahaan;
	}

	public void setGajiMax(int gajiMax){
		this.gajiMax = gajiMax;
	}

	public int getGajiMax(){
		return gajiMax;
	}

	public void setSyaratCv(String syaratCv){
		this.syaratCv = syaratCv;
	}

	public String getSyaratCv(){
		return syaratCv;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}


	public static class Entry implements BaseColumns {
		public static final String TABLE_NAME_PEKERJAAN="data_jasa_table";
		public static final String COLUMN_ID="ID";
		public static final String COLUMN_ID_KATEGORI="id_kategori";
		public static final String COLUMN_ID_PERUSAHAAN="id_perusahaan";
		public static final String COLUMN_NAMA_PERUSAHAAN="nama_perusahaan";
		public static final String COLUMN_EMAIL_PERUSAHAAN="email_perusahaan";
		public static final String COLUMN_PEKERJAAN="pekerjaan";
		public static final String COLUMN_GAJI_MIN="gaji_min";
		public static final String COLUMN_GAJI_MAX="gaji_max";
		public static final String COLUMN_DETAIL_PEKERJAAN="detail_pekerjaan";
		public static final String COLUMN_SYARAT_PEKERJAAN="syarat_pekerjaan";
		public static final String COLUMN_SYARAT_CV="syarat_cv";
		public static final String COLUMN_STATUS_VALIDASI="status_validasi";
	}

	@Override
 	public String toString(){
		return 
			"ResponsePekerjaan{" + 
			"id_kategori = '" + idKategori + '\'' + 
			",gaji_min = '" + gajiMin + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_perusahaan = '" + namaPerusahaan + '\'' +
					",email_perusahaan = '" + emailPerusahaan + '\'' +
			",status_validasi = '" + statusValidasi + '\'' + 
			",syarat_pekerjaan = '" + syaratPekerjaan + '\'' + 
			",detail pekerjaan = '" + detailPekerjaan + '\'' + 
			",pekerjaan = '" + pekerjaan + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id_perusahaan = '" + idPerusahaan + '\'' + 
			",gaji_max = '" + gajiMax + '\'' + 
			",syarat_cv = '" + syaratCv + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}