package de.hska.partsmanagement.business;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Service;

import de.hska.partsmanagement.domain.PartNode;

@Service
public class PartsNodeService {

	private PartNode bicycleChild;
	private PartNode bicycleMan;
	private PartNode bicycleWoman;

	public void initialize() {
		this.createChildTree();
		this.createWomanTree();
		this.createManTree();
	}

	public PartNode getNode(Integer partNumber) {
		PartNode wantedNode = this.searchInTreeForPartNode(this.bicycleChild, partNumber);
		if (wantedNode == null) {
			wantedNode = this.searchInTreeForPartNode(this.bicycleWoman, partNumber);
		}
		if (wantedNode == null) {
			wantedNode = this.searchInTreeForPartNode(this.bicycleMan, partNumber);
		}

		return wantedNode;
	}

	public PartNode searchInTreeForPartNode(PartNode tree, Integer partNumber) {
		if (Objects.equals(tree.getPartNumber(), partNumber)) {
			return tree;
		}
		if (!tree.hasParts()) {
			return null;
		}

		PartNode wantedPartNode = null;
		for (int i = 0; i < tree.getParts().size(); i++) {
			wantedPartNode = this.searchInTreeForPartNode(tree.getParts().get(i), partNumber);
			if (wantedPartNode != null) {
				break;
			}
		}
		return wantedPartNode;
	}

	public int getAmountInTree(PartNode tree, Integer partNumber) {
		int amount = 0;
		if (tree.getPartNumber().equals(partNumber)) {
			amount += tree.getAmount();
		}
		if (tree.hasParts()) {
			for (int i = 0; i < tree.getParts().size(); i++) {
				amount += this.getAmountInTree(tree.getParts().get(i), partNumber);
			}
		}
		return amount;
	}

	@SuppressWarnings("serial")
	public void createChildTree() {

		PartNode P1_K21 = new PartNode(21, 1, null);
		PartNode P1_K24 = new PartNode(24, 1, null);
		PartNode P1_K27 = new PartNode(27, 1, null);

		PartNode E26_K44 = new PartNode(44, 2, null);
		PartNode E26_K47 = new PartNode(47, 1, null);
		PartNode E26_K48 = new PartNode(48, 2, null);
		PartNode P1_E26 = new PartNode(26, 1, new ArrayList<PartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		PartNode E51_K24 = new PartNode(24, 1, null);
		PartNode E51_K27 = new PartNode(27, 1, null);

		PartNode E16_K24 = new PartNode(24, 1, null);
		PartNode E16_K28 = new PartNode(28, 1, null);
		PartNode E16_K40 = new PartNode(40, 1, null);
		PartNode E16_K41 = new PartNode(41, 1, null);
		PartNode E16_K42 = new PartNode(42, 2, null);
		PartNode E51_E16 = new PartNode(16, 1, new ArrayList<PartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		PartNode E17_K43 = new PartNode(43, 1, null);
		PartNode E17_K44 = new PartNode(44, 1, null);
		PartNode E17_K45 = new PartNode(45, 1, null);
		PartNode E17_K46 = new PartNode(46, 1, null);
		PartNode E51_E17 = new PartNode(17, 1, new ArrayList<PartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		PartNode E50_K24 = new PartNode(24, 2, null);
		PartNode E50_K25 = new PartNode(25, 2, null);

		PartNode E4_K35 = new PartNode(35, 2, null);
		PartNode E4_K36 = new PartNode(36, 1, null);
		PartNode E4_K52 = new PartNode(52, 1, null);
		PartNode E4_K53 = new PartNode(53, 36, null);
		PartNode E50_E4 = new PartNode(4, 1, new ArrayList<PartNode>() {
			{
				add(E4_K35);
				add(E4_K36);
				add(E4_K52);
				add(E4_K53);
			}
		});

		PartNode E10_K32 = new PartNode(32, 1, null);
		PartNode E10_K39 = new PartNode(39, 1, null);
		PartNode E50_E10 = new PartNode(10, 1, new ArrayList<PartNode>() {
			{
				add(E10_K32);
				add(E10_K39);
			}
		});

		PartNode E49_K24 = new PartNode(24, 2, null);
		PartNode E49_K25 = new PartNode(25, 2, null);

		PartNode E7_K35 = new PartNode(35, 2, null);
		PartNode E7_K37 = new PartNode(37, 1, null);
		PartNode E7_K38 = new PartNode(38, 1, null);
		PartNode E7_K52 = new PartNode(52, 1, null);
		PartNode E7_K53 = new PartNode(53, 36, null);
		PartNode E49_E7 = new PartNode(7, 1, new ArrayList<PartNode>() {
			{
				add(E7_K35);
				add(E7_K37);
				add(E7_K38);
				add(E7_K52);
				add(E7_K53);
			}
		});

		PartNode E13_K32 = new PartNode(32, 1, null);
		PartNode E13_K39 = new PartNode(39, 1, null);
		PartNode E49_E13 = new PartNode(13, 1, new ArrayList<PartNode>() {
			{
				add(E13_K32);
				add(E13_K39);
			}
		});

		PartNode E18_K28 = new PartNode(28, 3, null);
		PartNode E18_K32 = new PartNode(32, 1, null);
		PartNode E18_K59 = new PartNode(59, 2, null);
		PartNode E49_E18 = new PartNode(18, 1, new ArrayList<PartNode>() {
			{
				add(E18_K28);
				add(E18_K32);
				add(E18_K59);
			}
		});

		PartNode E50_E49 = new PartNode(49, 1, new ArrayList<PartNode>() {
			{
				add(E49_K24);
				add(E49_K25);
				add(E49_E7);
				add(E49_E13);
				add(E49_E18);
			}
		});

		PartNode E51_E50 = new PartNode(50, 1, new ArrayList<PartNode>() {
			{
				add(E50_K24);
				add(E50_K25);
				add(E50_E4);
				add(E50_E10);
				add(E50_E49);
			}
		});

		PartNode P1_E51 = new PartNode(51, 1, new ArrayList<PartNode>() {
			{
				add(E51_K24);
				add(E51_K27);
				add(E51_E16);
				add(E51_E17);
				add(E51_E50);
			}
		});

		PartNode P1 = new PartNode(1, 1, new ArrayList<PartNode>() {
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

	@SuppressWarnings("serial")
	public void createWomanTree() {
		PartNode P2_K22 = new PartNode(22, 1, null);
		PartNode P2_K24 = new PartNode(24, 1, null);
		PartNode P2_K27 = new PartNode(27, 1, null);

		PartNode E26_K44 = new PartNode(44, 2, null);
		PartNode E26_K47 = new PartNode(47, 1, null);
		PartNode E26_K48 = new PartNode(48, 2, null);
		PartNode P2_E26 = new PartNode(26, 1, new ArrayList<PartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		PartNode E56_K24 = new PartNode(24, 1, null);
		PartNode E56_K27 = new PartNode(27, 1, null);

		PartNode E16_K24 = new PartNode(24, 1, null);
		PartNode E16_K28 = new PartNode(28, 1, null);
		PartNode E16_K40 = new PartNode(40, 1, null);
		PartNode E16_K41 = new PartNode(41, 1, null);
		PartNode E16_K42 = new PartNode(42, 2, null);
		PartNode E56_E16 = new PartNode(16, 1, new ArrayList<PartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		PartNode E17_K43 = new PartNode(43, 1, null);
		PartNode E17_K44 = new PartNode(44, 1, null);
		PartNode E17_K45 = new PartNode(45, 1, null);
		PartNode E17_K46 = new PartNode(46, 1, null);
		PartNode E56_E17 = new PartNode(17, 1, new ArrayList<PartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		PartNode E55_K24 = new PartNode(24, 2, null);
		PartNode E55_K25 = new PartNode(25, 2, null);

		PartNode E5_K35 = new PartNode(35, 2, null);
		PartNode E5_K36 = new PartNode(36, 1, null);
		PartNode E5_K57 = new PartNode(57, 1, null);
		PartNode E5_K58 = new PartNode(58, 36, null);
		PartNode E55_E5 = new PartNode(5, 1, new ArrayList<PartNode>() {
			{
				add(E5_K35);
				add(E5_K36);
				add(E5_K57);
				add(E5_K58);
			}
		});

		PartNode E11_K32 = new PartNode(32, 1, null);
		PartNode E11_K39 = new PartNode(39, 1, null);
		PartNode E55_E11 = new PartNode(11, 1, new ArrayList<PartNode>() {
			{
				add(E11_K32);
				add(E11_K39);
			}
		});

		PartNode E54_K24 = new PartNode(24, 2, null);
		PartNode E54_K25 = new PartNode(25, 2, null);

		PartNode E8_K35 = new PartNode(35, 2, null);
		PartNode E8_K37 = new PartNode(37, 1, null);
		PartNode E8_K38 = new PartNode(38, 1, null);
		PartNode E8_K57 = new PartNode(57, 1, null);
		PartNode E8_K58 = new PartNode(58, 36, null);
		PartNode E54_E8 = new PartNode(8, 1, new ArrayList<PartNode>() {
			{
				add(E8_K35);
				add(E8_K37);
				add(E8_K38);
				add(E8_K57);
				add(E8_K58);
			}
		});

		PartNode E14_K32 = new PartNode(32, 1, null);
		PartNode E14_K39 = new PartNode(39, 1, null);
		PartNode E54_E14 = new PartNode(14, 1, new ArrayList<PartNode>() {
			{
				add(E14_K32);
				add(E14_K39);
			}
		});

		PartNode E19_K28 = new PartNode(28, 4, null);
		PartNode E19_K32 = new PartNode(32, 1, null);
		PartNode E19_K59 = new PartNode(59, 2, null);
		PartNode E54_E19 = new PartNode(19, 1, new ArrayList<PartNode>() {
			{
				add(E19_K28);
				add(E19_K32);
				add(E19_K59);
			}
		});

		PartNode E55_E54 = new PartNode(54, 1, new ArrayList<PartNode>() {
			{
				add(E54_K24);
				add(E54_K25);
				add(E54_E8);
				add(E54_E14);
				add(E54_E19);
			}
		});

		PartNode E56_E55 = new PartNode(55, 1, new ArrayList<PartNode>() {
			{
				add(E55_K24);
				add(E55_K25);
				add(E55_E5);
				add(E55_E11);
				add(E55_E54);
			}
		});

		PartNode P2_E56 = new PartNode(56, 1, new ArrayList<PartNode>() {
			{
				add(E56_K24);
				add(E56_K27);
				add(E56_E16);
				add(E56_E17);
				add(E56_E55);
			}
		});

		PartNode P2 = new PartNode(2, 1, new ArrayList<PartNode>() {
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

	@SuppressWarnings("serial")
	public void createManTree() {
		PartNode P3_K23 = new PartNode(23, 1, null);
		PartNode P3_K24 = new PartNode(24, 1, null);
		PartNode P3_K27 = new PartNode(27, 1, null);

		PartNode E26_K44 = new PartNode(44, 2, null);
		PartNode E26_K47 = new PartNode(47, 1, null);
		PartNode E26_K48 = new PartNode(48, 2, null);
		PartNode P3_E26 = new PartNode(26, 1, new ArrayList<PartNode>() {
			{
				add(E26_K44);
				add(E26_K47);
				add(E26_K48);
			}
		});

		PartNode E31_K24 = new PartNode(24, 1, null);
		PartNode E31_K27 = new PartNode(27, 1, null);

		PartNode E16_K24 = new PartNode(24, 1, null);
		PartNode E16_K28 = new PartNode(28, 1, null);
		PartNode E16_K40 = new PartNode(40, 1, null);
		PartNode E16_K41 = new PartNode(41, 1, null);
		PartNode E16_K42 = new PartNode(42, 2, null);
		PartNode E31_E16 = new PartNode(16, 1, new ArrayList<PartNode>() {
			{
				add(E16_K24);
				add(E16_K28);
				add(E16_K40);
				add(E16_K41);
				add(E16_K42);
			}
		});

		PartNode E17_K43 = new PartNode(43, 1, null);
		PartNode E17_K44 = new PartNode(44, 1, null);
		PartNode E17_K45 = new PartNode(45, 1, null);
		PartNode E17_K46 = new PartNode(46, 1, null);
		PartNode E31_E17 = new PartNode(17, 1, new ArrayList<PartNode>() {
			{
				add(E17_K43);
				add(E17_K44);
				add(E17_K45);
				add(E17_K46);
			}
		});

		PartNode E30_K24 = new PartNode(24, 2, null);
		PartNode E30_K25 = new PartNode(25, 2, null);

		PartNode E6_K33 = new PartNode(33, 1, null);
		PartNode E6_K34 = new PartNode(34, 36, null);
		PartNode E6_K35 = new PartNode(35, 2, null);
		PartNode E6_K36 = new PartNode(36, 1, null);
		PartNode E30_E6 = new PartNode(6, 1, new ArrayList<PartNode>() {
			{
				add(E6_K33);
				add(E6_K34);
				add(E6_K35);
				add(E6_K36);
			}
		});

		PartNode E12_K32 = new PartNode(32, 1, null);
		PartNode E12_K39 = new PartNode(39, 1, null);
		PartNode E30_E12 = new PartNode(12, 1, new ArrayList<PartNode>() {
			{
				add(E12_K32);
				add(E12_K39);
			}
		});

		PartNode E29_K24 = new PartNode(24, 2, null);
		PartNode E29_K25 = new PartNode(25, 2, null);

		PartNode E9_K33 = new PartNode(33, 1, null);
		PartNode E9_K34 = new PartNode(34, 36, null);
		PartNode E9_K35 = new PartNode(35, 2, null);
		PartNode E9_K37 = new PartNode(37, 1, null);
		PartNode E9_K38 = new PartNode(38, 1, null);
		PartNode E29_E9 = new PartNode(9, 1, new ArrayList<PartNode>() {
			{
				add(E9_K33);
				add(E9_K34);
				add(E9_K35);
				add(E9_K37);
				add(E9_K38);
			}
		});

		PartNode E15_K32 = new PartNode(32, 1, null);
		PartNode E15_K39 = new PartNode(39, 1, null);
		PartNode E29_E15 = new PartNode(15, 1, new ArrayList<PartNode>() {
			{
				add(E15_K32);
				add(E15_K39);
			}
		});

		PartNode E20_K28 = new PartNode(28, 5, null);
		PartNode E20_K32 = new PartNode(32, 1, null);
		PartNode E20_K59 = new PartNode(59, 2, null);
		PartNode E29_E20 = new PartNode(20, 1, new ArrayList<PartNode>() {
			{
				add(E20_K28);
				add(E20_K32);
				add(E20_K59);
			}
		});

		PartNode E30_E29 = new PartNode(29, 1, new ArrayList<PartNode>() {
			{
				add(E29_K24);
				add(E29_K25);
				add(E29_E9);
				add(E29_E15);
				add(E29_E20);
			}
		});

		PartNode E31_E30 = new PartNode(30, 1, new ArrayList<PartNode>() {
			{
				add(E30_K24);
				add(E30_K25);
				add(E30_E6);
				add(E30_E12);
				add(E30_E29);
			}
		});

		PartNode P3_E31 = new PartNode(31, 1, new ArrayList<PartNode>() {
			{
				add(E31_K24);
				add(E31_K27);
				add(E31_E16);
				add(E31_E17);
				add(E31_E30);
			}
		});

		PartNode P3 = new PartNode(3, 1, new ArrayList<PartNode>() {
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

	public PartNode getChildrenManufactoringNode() {
		return this.bicycleChild;
	}

	public PartNode getWomanManufactoringNode() {
		return this.bicycleWoman;
	}

	public PartNode getManManufactoringNode() {
		return this.bicycleMan;
	}
}
