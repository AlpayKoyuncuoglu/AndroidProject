package com.example.deneme.Models;

public class AsiModel{
	private Object asiIsim;
	private boolean tf;
	private Object petIsim;
	private Object asiTarih;
	private Object petTur;
	private Object petResim;
	private Object petCins;

	public void setAsiIsim(Object asiIsim){
		this.asiIsim = asiIsim;
	}

	public Object getAsiIsim(){
		return asiIsim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPetIsim(Object petIsim){
		this.petIsim = petIsim;
	}

	public Object getPetIsim(){
		return petIsim;
	}

	public void setAsiTarih(Object asiTarih){
		this.asiTarih = asiTarih;
	}

	public Object getAsiTarih(){
		return asiTarih;
	}

	public void setPetTur(Object petTur){
		this.petTur = petTur;
	}

	public Object getPetTur(){
		return petTur;
	}

	public void setPetResim(Object petResim){
		this.petResim = petResim;
	}

	public Object getPetResim(){
		return petResim;
	}

	public void setPetCins(Object petCins){
		this.petCins = petCins;
	}

	public Object getPetCins(){
		return petCins;
	}

	@Override
 	public String toString(){
		return 
			"AsiModel{" + 
			"asiIsim = '" + asiIsim + '\'' + 
			",tf = '" + tf + '\'' + 
			",petIsim = '" + petIsim + '\'' + 
			",asiTarih = '" + asiTarih + '\'' + 
			",petTur = '" + petTur + '\'' + 
			",petResim = '" + petResim + '\'' + 
			",petCins = '" + petCins + '\'' + 
			"}";
		}
}
