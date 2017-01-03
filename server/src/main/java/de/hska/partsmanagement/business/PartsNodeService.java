package de.hska.partsmanagement.business;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Service;

import de.hska.partsmanagement.domain.ManufacturingPartNode;

@Service
public class PartsNodeService {

	private ManufacturingPartNode bicycleChild;
	private ManufacturingPartNode bicycleMan;
	private ManufacturingPartNode bicycleWoman;

	public void initialize() {
		this.createChildTree();
		this.createWomanTree();
		this.createManTree();
	}

	public ManufacturingPartNode getNode(Integer partNumber) {
		ManufacturingPartNode wantedNode = this.searchInTreeForPartNode(this.bicycleChild, partNumber);
		if (wantedNode == null) {
			wantedNode = this.searchInTreeForPartNode(this.bicycleWoman, partNumber);
		}
		if (wantedNode == null) {
			wantedNode = this.searchInTreeForPartNode(this.bicycleMan, partNumber);
		}

		return wantedNode;
	}

	public ManufacturingPartNode searchInTreeForPartNode(ManufacturingPartNode tree, Integer partNumber) {
		if (Objects.equals(tree.getPartNumber(), partNumber)) {
			return tree;
		}
		if (!tree.hasParts()) {
			return null;
		}

		ManufacturingPartNode wantedPartNode = null;
		for (int i = 0; i < tree.getParts().size(); i++) {
			wantedPartNode = this.searchInTreeForPartNode(tree.getParts().get(i), partNumber);
			if (wantedPartNode != null) {
				break;
			}
		}
		return wantedPartNode;
	}

	public int getAmountInTree(ManufacturingPartNode tree, Integer partNumber) {
		int amount = 0;
		if (tree.getPartNumber().equals(partNumber)) {
			amount += tree.getAmount();
		}
		if (tree.hasCertainPart(partNumber)) {
			for (int i = 0; i < tree.getParts().size(); i++) {
				amount += this.getAmountInTree(tree.getParts().get(i), partNumber);
			}
		}
		return amount;
	}

	@SuppressWarnings("serial")
	public void createChildTree() {

		ManufacturingPartNode P1_K21 = new ManufacturingPartNode(21, 1, null);
		ManufacturingPartNode P1_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode P1_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E26_K44 = new ManufacturingPartNode(44, 2, null);
		ManufacturingPartNode E26_K47 = new ManufacturingPartNode(47, 1, null);
		ManufacturingPartNode E26_K48 = new ManufacturingPartNode(48, 2, null);
		ManufacturingPartNode P1_E26 = new ManufacturingPartNode(26, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		ManufacturingPartNode E51_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E51_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E16_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E16_K28 = new ManufacturingPartNode(28, 1, null);
		ManufacturingPartNode E16_K40 = new ManufacturingPartNode(40, 1, null);
		ManufacturingPartNode E16_K41 = new ManufacturingPartNode(41, 1, null);
		ManufacturingPartNode E16_K42 = new ManufacturingPartNode(42, 2, null);
		ManufacturingPartNode E51_E16 = new ManufacturingPartNode(16, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		ManufacturingPartNode E17_K43 = new ManufacturingPartNode(43, 1, null);
		ManufacturingPartNode E17_K44 = new ManufacturingPartNode(44, 1, null);
		ManufacturingPartNode E17_K45 = new ManufacturingPartNode(45, 1, null);
		ManufacturingPartNode E17_K46 = new ManufacturingPartNode(46, 1, null);
		ManufacturingPartNode E51_E17 = new ManufacturingPartNode(17, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		ManufacturingPartNode E50_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E50_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E4_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E4_K36 = new ManufacturingPartNode(36, 1, null);
		ManufacturingPartNode E4_K52 = new ManufacturingPartNode(52, 1, null);
		ManufacturingPartNode E4_K53 = new ManufacturingPartNode(53, 36, null);
		ManufacturingPartNode E50_E4 = new ManufacturingPartNode(4, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E4_K35);
				add(E4_K36);
				add(E4_K52);
				add(E4_K53);
			}
		});

		ManufacturingPartNode E10_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E10_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E50_E10 = new ManufacturingPartNode(10, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E10_K32);
				add(E10_K39);
			}
		});

		ManufacturingPartNode E49_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E49_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E7_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E7_K37 = new ManufacturingPartNode(37, 1, null);
		ManufacturingPartNode E7_K38 = new ManufacturingPartNode(38, 1, null);
		ManufacturingPartNode E7_K52 = new ManufacturingPartNode(52, 1, null);
		ManufacturingPartNode E7_K53 = new ManufacturingPartNode(53, 36, null);
		ManufacturingPartNode E49_E7 = new ManufacturingPartNode(7, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E7_K35);
				add(E7_K37);
				add(E7_K38);
				add(E7_K52);
				add(E7_K53);
			}
		});

		ManufacturingPartNode E13_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E13_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E49_E13 = new ManufacturingPartNode(13, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E13_K32);
				add(E13_K39);
			}
		});

		ManufacturingPartNode E18_K28 = new ManufacturingPartNode(28, 3, null);
		ManufacturingPartNode E18_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E18_K59 = new ManufacturingPartNode(59, 2, null);
		ManufacturingPartNode E49_E18 = new ManufacturingPartNode(18, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E18_K28);
				add(E18_K32);
				add(E18_K59);
			}
		});

		ManufacturingPartNode E50_E49 = new ManufacturingPartNode(49, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E49_K24);
				add(E49_K25);
				add(E49_E7);
				add(E49_E13);
				add(E49_E18);
			}
		});

		ManufacturingPartNode E51_E50 = new ManufacturingPartNode(50, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E50_K24);
				add(E50_K25);
				add(E50_E4);
				add(E50_E10);
				add(E50_E49);
			}
		});

		ManufacturingPartNode P1_E51 = new ManufacturingPartNode(51, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E51_K24);
				add(E51_K27);
				add(E51_E16);
				add(E51_E17);
				add(E51_E50);
			}
		});

		ManufacturingPartNode P1 = new ManufacturingPartNode(1, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(P1_K21);
				add(P1_K24);
				add(P1_K27);
				add(P1_E26);
				add(P1_E51);
			}
		});

		this.bicycleChild = P1;

	}

	public void createWomanTree() {
		ManufacturingPartNode P2_K22 = new ManufacturingPartNode(22, 1, null);
		ManufacturingPartNode P2_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode P2_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E26_K44 = new ManufacturingPartNode(44, 2, null);
		ManufacturingPartNode E26_K47 = new ManufacturingPartNode(47, 1, null);
		ManufacturingPartNode E26_K48 = new ManufacturingPartNode(48, 2, null);
		ManufacturingPartNode P2_E26 = new ManufacturingPartNode(26, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		ManufacturingPartNode E56_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E56_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E16_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E16_K28 = new ManufacturingPartNode(28, 1, null);
		ManufacturingPartNode E16_K40 = new ManufacturingPartNode(40, 1, null);
		ManufacturingPartNode E16_K41 = new ManufacturingPartNode(41, 1, null);
		ManufacturingPartNode E16_K42 = new ManufacturingPartNode(42, 2, null);
		ManufacturingPartNode E56_E16 = new ManufacturingPartNode(16, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		ManufacturingPartNode E17_K43 = new ManufacturingPartNode(43, 1, null);
		ManufacturingPartNode E17_K44 = new ManufacturingPartNode(44, 1, null);
		ManufacturingPartNode E17_K45 = new ManufacturingPartNode(45, 1, null);
		ManufacturingPartNode E17_K46 = new ManufacturingPartNode(46, 1, null);
		ManufacturingPartNode E56_E17 = new ManufacturingPartNode(17, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		ManufacturingPartNode E55_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E55_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E5_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E5_K36 = new ManufacturingPartNode(36, 1, null);
		ManufacturingPartNode E5_K57 = new ManufacturingPartNode(57, 1, null);
		ManufacturingPartNode E5_K58 = new ManufacturingPartNode(58, 36, null);
		ManufacturingPartNode E55_E5 = new ManufacturingPartNode(5, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E5_K35);
				add(E5_K36);
				add(E5_K57);
				add(E5_K58);
			}
		});

		ManufacturingPartNode E11_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E11_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E55_E11 = new ManufacturingPartNode(11, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E11_K32);
				add(E11_K39);
			}
		});

		ManufacturingPartNode E54_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E54_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E8_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E8_K37 = new ManufacturingPartNode(37, 1, null);
		ManufacturingPartNode E8_K38 = new ManufacturingPartNode(38, 1, null);
		ManufacturingPartNode E8_K57 = new ManufacturingPartNode(57, 1, null);
		ManufacturingPartNode E8_K58 = new ManufacturingPartNode(58, 36, null);
		ManufacturingPartNode E54_E8 = new ManufacturingPartNode(8, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E8_K35);
				add(E8_K37);
				add(E8_K38);
				add(E8_K57);
				add(E8_K58);
			}
		});

		ManufacturingPartNode E14_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E14_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E54_E14 = new ManufacturingPartNode(14, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E14_K32);
				add(E14_K39);
			}
		});

		ManufacturingPartNode E19_K28 = new ManufacturingPartNode(28, 4, null);
		ManufacturingPartNode E19_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E19_K59 = new ManufacturingPartNode(59, 2, null);
		ManufacturingPartNode E54_E19 = new ManufacturingPartNode(19, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E19_K28);
				add(E19_K32);
				add(E19_K59);
			}
		});

		ManufacturingPartNode E55_E54 = new ManufacturingPartNode(54, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E54_K24);
				add(E54_K25);
				add(E54_E8);
				add(E54_E14);
				add(E54_E19);
			}
		});

		ManufacturingPartNode E56_E55 = new ManufacturingPartNode(55, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E55_K24);
				add(E55_K25);
				add(E55_E5);
				add(E55_E11);
				add(E55_E54);
			}
		});

		ManufacturingPartNode P2_E56 = new ManufacturingPartNode(56, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E56_K24);
				add(E56_K27);
				add(E56_E16);
				add(E56_E17);
				add(E56_E55);
			}
		});

		ManufacturingPartNode P2 = new ManufacturingPartNode(2, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(P2_K22);
				add(P2_K24);
				add(P2_K27);
				add(P2_E26);
				add(P2_E56);
			}
		});

		this.bicycleWoman = P2;
	}

	public void createManTree() {
		ManufacturingPartNode P3_K23 = new ManufacturingPartNode(23, 1, null);
		ManufacturingPartNode P3_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode P3_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E26_K44 = new ManufacturingPartNode(44, 2, null);
		ManufacturingPartNode E26_K47 = new ManufacturingPartNode(47, 1, null);
		ManufacturingPartNode E26_K48 = new ManufacturingPartNode(48, 2, null);
		ManufacturingPartNode P3_E26 = new ManufacturingPartNode(26, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		ManufacturingPartNode E31_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E31_K27 = new ManufacturingPartNode(27, 1, null);

		ManufacturingPartNode E16_K24 = new ManufacturingPartNode(24, 1, null);
		ManufacturingPartNode E16_K28 = new ManufacturingPartNode(28, 1, null);
		ManufacturingPartNode E16_K40 = new ManufacturingPartNode(40, 1, null);
		ManufacturingPartNode E16_K41 = new ManufacturingPartNode(41, 1, null);
		ManufacturingPartNode E16_K42 = new ManufacturingPartNode(42, 2, null);
		ManufacturingPartNode E31_E16 = new ManufacturingPartNode(16, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		ManufacturingPartNode E17_K43 = new ManufacturingPartNode(43, 1, null);
		ManufacturingPartNode E17_K44 = new ManufacturingPartNode(44, 1, null);
		ManufacturingPartNode E17_K45 = new ManufacturingPartNode(45, 1, null);
		ManufacturingPartNode E17_K46 = new ManufacturingPartNode(46, 1, null);
		ManufacturingPartNode E31_E17 = new ManufacturingPartNode(17, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		ManufacturingPartNode E30_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E30_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E6_K33 = new ManufacturingPartNode(33, 1, null);
		ManufacturingPartNode E6_K34 = new ManufacturingPartNode(34, 36, null);
		ManufacturingPartNode E6_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E6_K36 = new ManufacturingPartNode(36, 1, null);
		ManufacturingPartNode E30_E6 = new ManufacturingPartNode(6, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E6_K33);
				add(E6_K34);
				add(E6_K35);
				add(E6_K36);
			}
		});

		ManufacturingPartNode E12_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E12_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E30_E12 = new ManufacturingPartNode(12, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E12_K32);
				add(E12_K39);
			}
		});

		ManufacturingPartNode E29_K24 = new ManufacturingPartNode(24, 2, null);
		ManufacturingPartNode E29_K25 = new ManufacturingPartNode(25, 2, null);

		ManufacturingPartNode E9_K33 = new ManufacturingPartNode(33, 1, null);
		ManufacturingPartNode E9_K34 = new ManufacturingPartNode(34, 36, null);
		ManufacturingPartNode E9_K35 = new ManufacturingPartNode(35, 2, null);
		ManufacturingPartNode E9_K37 = new ManufacturingPartNode(37, 1, null);
		ManufacturingPartNode E9_K38 = new ManufacturingPartNode(38, 1, null);
		ManufacturingPartNode E29_E9 = new ManufacturingPartNode(9, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E9_K33);
				add(E9_K34);
				add(E9_K35);
				add(E9_K37);
				add(E9_K38);
			}
		});

		ManufacturingPartNode E15_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E15_K39 = new ManufacturingPartNode(39, 1, null);
		ManufacturingPartNode E29_E15 = new ManufacturingPartNode(15, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E15_K32);
				add(E15_K39);
			}
		});

		ManufacturingPartNode E20_K28 = new ManufacturingPartNode(28, 5, null);
		ManufacturingPartNode E20_K32 = new ManufacturingPartNode(32, 1, null);
		ManufacturingPartNode E20_K59 = new ManufacturingPartNode(59, 2, null);
		ManufacturingPartNode E29_E20 = new ManufacturingPartNode(20, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E20_K28);
				add(E20_K32);
				add(E20_K59);
			}
		});

		ManufacturingPartNode E30_E29 = new ManufacturingPartNode(29, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E29_K24);
				add(E29_K25);
				add(E29_E9);
				add(E29_E15);
				add(E29_E20);
			}
		});

		ManufacturingPartNode E31_E30 = new ManufacturingPartNode(30, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E30_K24);
				add(E30_K25);
				add(E30_E6);
				add(E30_E12);
				add(E30_E29);
			}
		});

		ManufacturingPartNode P3_E31 = new ManufacturingPartNode(31, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(E31_K24);
				add(E31_K27);
				add(E31_E16);
				add(E31_E17);
				add(E31_E30);
			}
		});

		ManufacturingPartNode P3 = new ManufacturingPartNode(3, 1, new ArrayList<ManufacturingPartNode>() {
			{
				add(P3_K23);
				add(P3_K24);
				add(P3_K27);
				add(P3_E26);
				add(P3_E31);
			}
		});

		this.bicycleMan = P3;
	}

	public ManufacturingPartNode getChildrenManufactoringNode() {
		return this.bicycleChild;
	}

	public ManufacturingPartNode getWomanManufactoringNode() {
		return this.bicycleWoman;
	}

	public ManufacturingPartNode getManManufactoringNode() {
		return this.bicycleMan;
	}
}
