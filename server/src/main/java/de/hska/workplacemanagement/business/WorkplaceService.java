package de.hska.workplacemanagement.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import de.hska.workplacemanagement.domain.ProductionLine;
import de.hska.workplacemanagement.domain.Workplace;
import de.hska.workplacemanagement.domain.WorkplaceNode;

@Service
public class WorkplaceService {

	private Map<Integer, ProductionLine> productionLineMap;
	private ArrayList<ProductionLine> allProductionLines;
	private ArrayList<Workplace> allWorkPlaces;

	public void initialize() {
		this.productionLineMap = new HashMap<>();
		this.setWorkplaces();
	}

	private void setWorkplaces() {
		this.allWorkPlaces = new ArrayList<Workplace>() {
			{
				// These are used in all Products, added only once
				add(new Workplace(15, 17, 3, 15));

				add(new Workplace(7, 26, 2, 30));
				add(new Workplace(15, 26, 3, 15));

				add(new Workplace(6, 16, 2, 15));
				add(new Workplace(14, 16, 3, 0));

				// Workplaces for P1
				add(new Workplace(13, 13, 2, 0));
				add(new Workplace(12, 13, 3, 0));
				add(new Workplace(8, 13, 1, 15));
				add(new Workplace(7, 13, 2, 20));
				add(new Workplace(9, 13, 3, 15));

				add(new Workplace(6, 18, 3, 15));
				add(new Workplace(8, 18, 3, 20));
				add(new Workplace(7, 18, 2, 20));
				add(new Workplace(9, 18, 2, 15));

				add(new Workplace(10, 7, 4, 20));
				add(new Workplace(11, 7, 3, 20));

				add(new Workplace(10, 4, 4, 20));
				add(new Workplace(11, 4, 3, 10));

				add(new Workplace(13, 10, 2, 0));
				add(new Workplace(12, 10, 3, 0));
				add(new Workplace(8, 10, 1, 15));
				add(new Workplace(7, 10, 2, 20));
				add(new Workplace(9, 10, 3, 15));

				add(new Workplace(1, 49, 6, 20));

				add(new Workplace(2, 50, 5, 30));

				add(new Workplace(3, 51, 5, 20));

				add(new Workplace(4, 1, 6, 30));

				// Workplaces for P2
				add(new Workplace(13, 14, 2, 0));
				add(new Workplace(12, 14, 3, 0));
				add(new Workplace(8, 14, 2, 15));
				add(new Workplace(7, 14, 2, 20));
				add(new Workplace(9, 14, 3, 15));

				add(new Workplace(6, 19, 3, 15));
				add(new Workplace(8, 19, 3, 25));
				add(new Workplace(7, 19, 2, 20));
				add(new Workplace(9, 19, 2, 20));

				add(new Workplace(10, 8, 4, 20));
				add(new Workplace(11, 8, 3, 20));

				add(new Workplace(10, 5, 4, 20));
				add(new Workplace(11, 5, 3, 10));

				add(new Workplace(13, 11, 2, 0));
				add(new Workplace(12, 11, 3, 0));
				add(new Workplace(8, 11, 2, 15));
				add(new Workplace(7, 11, 2, 20));
				add(new Workplace(9, 11, 3, 15));

				add(new Workplace(1, 54, 6, 20));

				add(new Workplace(2, 55, 5, 30));

				add(new Workplace(3, 56, 6, 20));

				add(new Workplace(4, 2, 7, 20));

				// Workplaces for P3
				add(new Workplace(13, 15, 2, 0));
				add(new Workplace(12, 15, 3, 0));
				add(new Workplace(8, 15, 2, 15));
				add(new Workplace(7, 15, 2, 20));
				add(new Workplace(9, 15, 3, 15));

				add(new Workplace(6, 20, 3, 15));
				add(new Workplace(8, 20, 3, 20));
				add(new Workplace(7, 20, 2, 20));
				add(new Workplace(9, 20, 2, 15));

				add(new Workplace(10, 9, 4, 20));
				add(new Workplace(11, 9, 3, 20));

				add(new Workplace(10, 6, 4, 20));
				add(new Workplace(11, 6, 3, 20));

				add(new Workplace(13, 12, 2, 0));
				add(new Workplace(12, 12, 3, 0));
				add(new Workplace(8, 12, 2, 15));
				add(new Workplace(7, 12, 2, 20));
				add(new Workplace(9, 12, 3, 15));

				add(new Workplace(1, 29, 6, 20));

				add(new Workplace(2, 30, 5, 20));

				add(new Workplace(3, 31, 6, 20));

				add(new Workplace(4, 3, 7, 30));
			}
		};

		// Production line for P1 (lines for E16, E17 and E26 are only once
		// added here even if they are used in all products)
		// That is because of the HashMap mechanism
		this.productionLineMap.put(16,
				new ProductionLine(this.getWorkplaceNode(6, 16, this.getWorkplaceNode(14, 16, null))));
		this.productionLineMap.put(17, new ProductionLine(this.getWorkplaceNode(15, 17, null)));
		this.productionLineMap.put(26,
				new ProductionLine(this.getWorkplaceNode(7, 26, this.getWorkplaceNode(15, 26, null))));

		// Production line for P1
		this.productionLineMap.put(13, new ProductionLine(this.getWorkplaceNode(13, 13, this.getWorkplaceNode(12, 13,
				this.getWorkplaceNode(8, 13, this.getWorkplaceNode(7, 13, this.getWorkplaceNode(9, 13, null)))))));
		this.productionLineMap.put(18, new ProductionLine(this.getWorkplaceNode(6, 18,
				this.getWorkplaceNode(8, 18, this.getWorkplaceNode(7, 18, this.getWorkplaceNode(9, 18, null))))));
		this.productionLineMap.put(7,
				new ProductionLine(this.getWorkplaceNode(10, 7, this.getWorkplaceNode(11, 7, null))));
		this.productionLineMap.put(4,
				new ProductionLine(this.getWorkplaceNode(10, 4, this.getWorkplaceNode(11, 4, null))));
		this.productionLineMap.put(10, new ProductionLine(this.getWorkplaceNode(13, 10, this.getWorkplaceNode(12, 10,
				this.getWorkplaceNode(8, 10, this.getWorkplaceNode(7, 10, this.getWorkplaceNode(9, 10, null)))))));
		this.productionLineMap.put(49, new ProductionLine(this.getWorkplaceNode(1, 49, null)));
		this.productionLineMap.put(50, new ProductionLine(this.getWorkplaceNode(2, 50, null)));
		this.productionLineMap.put(51, new ProductionLine(this.getWorkplaceNode(3, 51, null)));
		this.productionLineMap.put(1, new ProductionLine(this.getWorkplaceNode(4, 1, null)));

		// Production line for P2
		this.productionLineMap.put(14, new ProductionLine(this.getWorkplaceNode(13, 14, this.getWorkplaceNode(12, 14,
				this.getWorkplaceNode(8, 14, this.getWorkplaceNode(7, 14, this.getWorkplaceNode(9, 14, null)))))));
		this.productionLineMap.put(19, new ProductionLine(this.getWorkplaceNode(6, 19,
				this.getWorkplaceNode(8, 19, this.getWorkplaceNode(7, 19, this.getWorkplaceNode(9, 19, null))))));
		this.productionLineMap.put(8,
				new ProductionLine(this.getWorkplaceNode(10, 8, this.getWorkplaceNode(11, 8, null))));
		this.productionLineMap.put(5,
				new ProductionLine(this.getWorkplaceNode(10, 5, this.getWorkplaceNode(11, 5, null))));
		this.productionLineMap.put(11, new ProductionLine(this.getWorkplaceNode(13, 11, this.getWorkplaceNode(12, 11,
				this.getWorkplaceNode(8, 11, this.getWorkplaceNode(7, 11, this.getWorkplaceNode(9, 11, null)))))));
		this.productionLineMap.put(54, new ProductionLine(this.getWorkplaceNode(1, 54, null)));
		this.productionLineMap.put(55, new ProductionLine(this.getWorkplaceNode(2, 55, null)));
		this.productionLineMap.put(56, new ProductionLine(this.getWorkplaceNode(3, 56, null)));
		this.productionLineMap.put(2, new ProductionLine(this.getWorkplaceNode(4, 2, null)));

		// Production line for P3
		this.productionLineMap.put(15, new ProductionLine(this.getWorkplaceNode(13, 15, this.getWorkplaceNode(12, 15,
				this.getWorkplaceNode(8, 15, this.getWorkplaceNode(7, 15, this.getWorkplaceNode(9, 15, null)))))));
		this.productionLineMap.put(20, new ProductionLine(this.getWorkplaceNode(6, 20,
				this.getWorkplaceNode(8, 20, this.getWorkplaceNode(7, 20, this.getWorkplaceNode(9, 20, null))))));
		this.productionLineMap.put(9,
				new ProductionLine(this.getWorkplaceNode(10, 9, this.getWorkplaceNode(11, 9, null))));
		this.productionLineMap.put(6,
				new ProductionLine(this.getWorkplaceNode(10, 6, this.getWorkplaceNode(11, 6, null))));
		this.productionLineMap.put(12, new ProductionLine(this.getWorkplaceNode(13, 12, this.getWorkplaceNode(12, 12,
				this.getWorkplaceNode(8, 12, this.getWorkplaceNode(7, 12, this.getWorkplaceNode(9, 12, null)))))));
		this.productionLineMap.put(29, new ProductionLine(this.getWorkplaceNode(1, 29, null)));
		this.productionLineMap.put(30, new ProductionLine(this.getWorkplaceNode(2, 30, null)));
		this.productionLineMap.put(31, new ProductionLine(this.getWorkplaceNode(3, 31, null)));
		this.productionLineMap.put(3, new ProductionLine(this.getWorkplaceNode(4, 3, null)));

		this.allProductionLines = new ArrayList<ProductionLine>() {
			{
				// All
				add(productionLineMap.get(17));
				add(productionLineMap.get(16));
				add(productionLineMap.get(26));

				// P1
				add(productionLineMap.get(13));
				add(productionLineMap.get(18));
				add(productionLineMap.get(7));
				add(productionLineMap.get(4));
				add(productionLineMap.get(10));
				add(productionLineMap.get(49));
				add(productionLineMap.get(50));
				add(productionLineMap.get(51));
				add(productionLineMap.get(1));

				// P2
				add(productionLineMap.get(14));
				add(productionLineMap.get(19));
				add(productionLineMap.get(8));
				add(productionLineMap.get(5));
				add(productionLineMap.get(11));
				add(productionLineMap.get(54));
				add(productionLineMap.get(55));
				add(productionLineMap.get(56));
				add(productionLineMap.get(2));

				// P3
				add(productionLineMap.get(15));
				add(productionLineMap.get(20));
				add(productionLineMap.get(9));
				add(productionLineMap.get(29));
				add(productionLineMap.get(6));
				add(productionLineMap.get(12));
				add(productionLineMap.get(30));
				add(productionLineMap.get(31));
				add(productionLineMap.get(3));
			}
		};
	}

	public WorkplaceNode getWorkplaceNode(Integer number, Integer part, WorkplaceNode follower) {
		for (int i = 0; i < this.allWorkPlaces.size(); i++) {
			if (Objects.equals(this.allWorkPlaces.get(i).getNumber(), number)
					&& Objects.equals(this.allWorkPlaces.get(i).getOutputProductId(), part)) {
				return new WorkplaceNode(this.allWorkPlaces.get(i), follower);
			}
		}
		return null;
	}

	public void reset() {
		for (int i = 0; i < this.allProductionLines.size(); i++) {
			this.allProductionLines.get(i).reset();
		}
	}

	public Integer getArbeitsplatzId(Integer part) {
		for (int i = 0; i < this.allWorkPlaces.size(); i++) {
			if (Objects.equals(this.allWorkPlaces.get(i).getOutputProductId(), part)) {
				this.allWorkPlaces.get(i).getNumber();
			}
		}
		return 0;
	}

	public Map<Integer, ProductionLine> getProductionLineMap() {
		return this.productionLineMap;
	}
}
