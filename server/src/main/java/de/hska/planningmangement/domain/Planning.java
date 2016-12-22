package de.hska.planningmangement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.hska.filemanagement.domain.JsonFile;

@Entity
public class Planning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private JsonFile jsonFile;

	public Planning() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JsonFile getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(JsonFile jsonFile) {
		this.jsonFile = jsonFile;
	}

}
