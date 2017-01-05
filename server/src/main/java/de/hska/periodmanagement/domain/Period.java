package de.hska.periodmanagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.planningmangement.domain.Planning;

@Entity
@Table(name = "period")
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long counter;

	@OneToOne(cascade = CascadeType.ALL)
	private JsonFile result;

	@OneToOne(cascade = CascadeType.ALL)
	private Planning planning;

	@OneToOne(cascade = CascadeType.ALL)
	private JsonFile input;

	public Period() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public JsonFile getResult() {
		return result;
	}

	public void setResult(JsonFile result) {
		this.result = result;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	public JsonFile getInput() {
		return input;
	}

	public void setInput(JsonFile input) {
		this.input = input;
	}

}
