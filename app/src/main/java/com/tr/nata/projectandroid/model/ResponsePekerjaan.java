package com.tr.nata.projectandroid.model;

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

	@Override
 	public String toString(){
		return 
			"ResponsePekerjaan{" + 
			"id_kategori = '" + idKategori + '\'' + 
			",gaji_min = '" + gajiMin + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_perusahaan = '" + namaPerusahaan + '\'' + 
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