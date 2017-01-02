package de.hska.partsmanagement.business;

import de.hska.procurementmanagement.domain.BuyPart;
import de.hska.productionmanagement.domain.ManufacturingPart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class PartsService {

    ArrayList<BuyPart> buyParts;
    ArrayList<ManufacturingPart> manufacturingParts;

    public void inizialize() {
        this.buyParts = new ArrayList<BuyPart>() {
            {
                add(new BuyPart(21, "Kette(K)", 5.00, 300, false, 300, 50.0, 1.8, 0.4));
                add(new BuyPart(22, "Kette(D)", 6.50, 300, false, 300, 50.0, 1.7, 0.4));
                add(new BuyPart(23, "Kette(H)", 6.50, 300, false, 300, 50.0, 1.2, 0.2));
                add(new BuyPart(24, "Mutter 3/8", 0.06, 6100, true, 6100, 100.0, 3.2, 0.3));
                add(new BuyPart(25, "Scheibe 3/8", 0.06, 3600, true, 3600, 50.0, 0.9, 0.2));
                add(new BuyPart(27, "Schraube 3/8", 0.10, 1800, true, 1800, 75.0, 0.9, 0.2));
                add(new BuyPart(28, "Rohr 3/4", 1.20, 4500, true, 4500, 50.0, 1.7, 0.4));
                add(new BuyPart(32, "Farbe", 0.75, 2700, true, 2700, 50.0, 2.1, 0.5));
                add(new BuyPart(33, "Felge cpl.(H)", 22.00, 900, false, 900, 75.0, 1.9, 0.5));
                add(new BuyPart(34, "Speiche(H)", 0.10, 22000, false, 22000, 50.0, 1.6, 0.3));
                add(new BuyPart(35, "Konus", 1.00, 3600, true, 3600, 75.0, 2.2, 0.4));
                add(new BuyPart(36, "Freilauf", 8.00, 900, true, 900, 100.0, 1.2, 0.1));
                add(new BuyPart(37, "Gabel", 1.50, 900, true, 900, 50.0, 1.5, 0.3));
                add(new BuyPart(38, "Welle", 1.50, 300, true, 300, 50.0, 1.7, 0.4));
                add(new BuyPart(39, "Blech", 1.50, 900, true, 900, 75.0, 1.5, 0.3));
                add(new BuyPart(40, "Lenker", 2.50, 900, true, 900, 50.0, 1.7, 0.2));
                add(new BuyPart(41, "Mutter 3/4", 0.06, 900, true, 900, 50.0, 0.9, 0.2));
                add(new BuyPart(42, "Griff", 0.10, 1800, true, 1800, 50.0, 1.2, 0.3));
                add(new BuyPart(43, "Griff", 5.00, 1900, true, 1900, 75.0, 2.0, 0.5));
                add(new BuyPart(44, "Stange 1/2", 0.50, 2700, true, 2700, 50.0, 1.0, 0.3));
                add(new BuyPart(45, "Mutter 1/4", 0.06, 900, true, 900, 50.0, 1.7, 0.3));
                add(new BuyPart(46, "Schraube 1/4", 0.10, 900, true, 900, 50.0, 0.9, 0.3));
                add(new BuyPart(47, "Zahnkranz", 3.50, 900, true, 900, 50.0, 1.41, 0.1));
                add(new BuyPart(48, "Pedal", 1.50, 1800, true, 1800, 75.0, 1.0, 0.2));
                add(new BuyPart(52, "Felge cpl.(K)", 22.00, 600, false, 600, 50.0, 1.6, 0.4));
                add(new BuyPart(53, "Speiche(K)", 0.10, 22000, false, 22000, 50.0, 1.6, 0.2));
                add(new BuyPart(57, "Felge cpl.(D)", 22.00, 600, false, 600, 50.0, 1.7, 0.3));
                add(new BuyPart(58, "Speiche(D)", 0.10, 22000, false, 22000, 50.0, 1.6, 0.5));
                add(new BuyPart(59, "Schweißdraht", 0.15, 1800, true, 1800, 50.0, 1.7, 0.2));
            }
        };

        this.manufacturingParts = new ArrayList<ManufacturingPart>() {
            {
                add(new ManufacturingPart(1, "Kinderfahrrad", 156.13, 100, false));
                add(new ManufacturingPart(2, "Damenfahrrad", 163.33, 100, false));
                add(new ManufacturingPart(3, "Herrenfahrrad", 165.08, 100, false));
                add(new ManufacturingPart(4, "Hinterradgruppe(K)", 40.85, 100, false));
                add(new ManufacturingPart(5, "Hinterradgruppe(D)", 39.85, 100, false));
                add(new ManufacturingPart(6, "Hinterradgruppe(H)", 40.85, 100, false));
                add(new ManufacturingPart(7, "Vorderradgruppe(K)", 35.85, 100, false));
                add(new ManufacturingPart(8, "Vorderradgruppe(D)", 35.85, 100, false));
                add(new ManufacturingPart(9, "Vorderradgruppe(H)", 35.85, 100, false));
                add(new ManufacturingPart(10, "Schutzblech h.(K)", 12.40, 100, false));
                add(new ManufacturingPart(11, "Schutzblech h.(D)", 14.65, 100, false));
                add(new ManufacturingPart(12, "Schutzblech h.(H)", 14.65, 100, false));
                add(new ManufacturingPart(13, "Schutzblech v.(K)", 12.40, 100, false));
                add(new ManufacturingPart(14, "Schutzblech v.(D)", 14.65, 100, false));
                add(new ManufacturingPart(15, "Schutzblech v.(H)", 14.65, 100, false));
                add(new ManufacturingPart(16, "Lenker cpl.", 7.02, 300, true));
                add(new ManufacturingPart(17, "Sattel cpl.", 7.16, 300, true));
                add(new ManufacturingPart(18, "Rahmen(K)", 13.15, 100, false));
                add(new ManufacturingPart(19, "Rahmen(D)", 14.35, 100, false));
                add(new ManufacturingPart(20, "Rahmen(H)", 15.55, 100, false));
                add(new ManufacturingPart(26, "Pedal cpl.", 10.50, 300, true));
                add(new ManufacturingPart(29, "Vorderrad mont.", 69.29, 100, false));
                add(new ManufacturingPart(30, "Rahmen u. Räder", 127.53, 100, false));
                add(new ManufacturingPart(31, "Fahrrad o. Ped.", 144.42, 100, false));
                add(new ManufacturingPart(49, "Vorderrad cpl.", 64.64, 100, false));
                add(new ManufacturingPart(50, "Rahmen u. Räder", 120.63, 100, false));
                add(new ManufacturingPart(51, "Fahrrad o. Pedal", 137.47, 100, false));
                add(new ManufacturingPart(54, "Vorderrad cpl.", 68.09, 100, false));
                add(new ManufacturingPart(55, "Rahmen u. Räder", 125.33, 100, false));
                add(new ManufacturingPart(56, "Fahrrad o. Pedal", 142.67, 100, false));
            }
        };
    }

    public ArrayList<BuyPart> getBuyParts() {
        return buyParts;
    }

    public void setBuyParts(ArrayList<BuyPart> buyParts) {
        this.buyParts = buyParts;
    }

    public ArrayList<ManufacturingPart> getManufacturingParts() {
        return manufacturingParts;
    }

    public void setManufacturingParts(ArrayList<ManufacturingPart> manufacturingParts) {
        this.manufacturingParts = manufacturingParts;
    }

    public BuyPart getBuyPartById(Integer id) {
        return buyParts
                .stream()
                .filter(x -> x.getNumber() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    public ManufacturingPart getManufacturingPartById(Integer id) {
        return manufacturingParts
                .stream()
                .filter(x -> x.getNumber() == id)
                .collect(Collectors.toList())
                .get(0);
    }
}
