package de.hska.capacitymanagement.domain;

public class Capacity {

	private int shift;
	private int overtime;
	private int station;

	private Boolean tooMuchWork;

	public Capacity() {
		this.tooMuchWork = false;
	}

	public Capacity(int shift, int overtime, int station) {
		super();
		this.shift = shift;
		this.overtime = overtime;
		this.station = station;
		this.tooMuchWork = false;
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

	public Boolean getTooMuchWork() {
		return tooMuchWork;
	}

	public void setTooMuchWork(Boolean tooMuchWork) {
		this.tooMuchWork = tooMuchWork;
	}
}
