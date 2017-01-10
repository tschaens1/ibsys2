package de.hska.capacitymanagement.domain;

public class Capacity {

	private int shift;
	private int overtime;
	private int station;

	public Capacity() {

	}

	public Capacity(int shift, int overtime, int station) {
		super();
		this.shift = shift;
		this.overtime = overtime;
		this.station = station;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

}
