package nl.boksebeld.domein.plant;

import java.io.Serializable;

public class Maand implements Serializable {

	private static final long serialVersionUID = -7902726384841071054L;

	private int id;

	private String maand;

	private Bloeitijd bloeitijd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaand() {
		return maand;
	}

	public void setMaand(String maand) {
		this.maand = maand;
	}

	public Bloeitijd getBloeitijd() {
		return bloeitijd;
	}

	public void setBloeitijd(Bloeitijd bloeitijd) {
		this.bloeitijd = bloeitijd;
	}

}
