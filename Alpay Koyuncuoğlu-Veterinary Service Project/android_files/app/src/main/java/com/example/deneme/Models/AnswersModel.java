package com.example.deneme.Models;

public class AnswersModel{
	private String cevapId;
	private String cevap;
	private boolean tf;
	private String soruId;
	private String soru;

	public void setCevapId(String cevapId){
		this.cevapId = cevapId;
	}

	public String getCevapId(){
		return cevapId;
	}

	public void setCevap(String cevap){
		this.cevap = cevap;
	}

	public String getCevap(){
		return cevap;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setSoruId(String soruId){
		this.soruId = soruId;
	}

	public String getSoruId(){
		return soruId;
	}

	public void setSoru(String soru){
		this.soru = soru;
	}

	public String getSoru(){
		return soru;
	}

	@Override
 	public String toString(){
		return 
			"AnswersModel{" + 
			"cevapId = '" + cevapId + '\'' + 
			",cevap = '" + cevap + '\'' + 
			",tf = '" + tf + '\'' + 
			",soruId = '" + soruId + '\'' + 
			",soru = '" + soru + '\'' + 
			"}";
		}
}
