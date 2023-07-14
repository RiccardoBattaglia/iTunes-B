package it.polito.tdp.itunes.model;

import java.util.Objects;

public class Dettaglio implements Comparable<Dettaglio> {
	
	Album album;
	double bilancio;
	
	public Dettaglio(Album album, double bilancio) {
		super();
		this.album = album;
		this.bilancio = bilancio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, bilancio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dettaglio other = (Dettaglio) obj;
		return Objects.equals(album, other.album)
				&& Double.doubleToLongBits(bilancio) == Double.doubleToLongBits(other.bilancio);
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public double getBilancio() {
		return bilancio;
	}

	public void setBilancio(double bilancio) {
		this.bilancio = bilancio;
	}

	@Override
	public int compareTo(Dettaglio o) {
		// TODO Auto-generated method stub
		return Double .compare(o.bilancio, this.bilancio);
	}
	
	

}
