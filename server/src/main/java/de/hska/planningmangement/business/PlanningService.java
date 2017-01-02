package de.hska.planningmangement.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.hska.filemanagement.domain.JsonFile;
import de.hska.kpimanagement.domain.DirectSale;

public class PlanningService {

	private List<Object> productionProgramm = new ArrayList<>();
	private List<DirectSale> directSales = new ArrayList<>();
	
	public void initialize(JsonFile jsonFile)
            throws ParseException {
		this.createProductionPosition();
		this.createDirectSalesPosition();
	}
	
	public void createProductionPosition(){
		
	}
	
	public void createDirectSalesPosition(){
		
	}
	
	public void getProgrammPosition(){
		
	}
	
	public void getDirectSalesPosition(){
		
	}
}
