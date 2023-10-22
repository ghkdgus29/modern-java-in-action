package chap19;

public class PersistentTrainJourney {

    public static void main(String[] args) {
        System.out.println("Destructive update:");
        TrainJourney firstJourney = new TrainJourney(1, null);
        TrainJourney secondJourney = new TrainJourney(2, null);
        TrainJourney xToZ = link(firstJourney, secondJourney);
        System.out.printf("firstJourney (X to Y) = %s%n", firstJourney);
        System.out.printf("secondJourney (Y to Z) = %s%n", secondJourney);
        System.out.printf("X to Z = %s%n", xToZ);

        System.out.println();
        System.out.println("The functional way:");
        firstJourney = new TrainJourney(1, null);
        secondJourney = new TrainJourney(2, null);
        xToZ = append(firstJourney, secondJourney);
        System.out.printf("firstJourney (X to Y) = %s%n", firstJourney);
        System.out.printf("secondJourney (Y to Z) = %s%n", secondJourney);
        System.out.printf("X to Z = %s%n", xToZ);
    }

    public static TrainJourney link(TrainJourney a, TrainJourney b) {       // 부작용 발생
        if (a == null) {
            return b;
        }

        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    public static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    static class TrainJourney {

        public int price;
        public TrainJourney onward;

        public TrainJourney(int price, TrainJourney t) {
            this.price = price;
            this.onward = t;
        }

        @Override
        public String toString() {
            return String.format("TrainJourney[%d] -> %s", price, onward);
        }
    }
}

