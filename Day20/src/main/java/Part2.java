import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Part2 {
    private static final int input = 33100000;
    private static HashSet<Integer> exhaustedElves = new HashSet<>();

    public static void main(String[] args) {

        int house = 0;
        int presents = 0;
        ArrayList<Integer> deliveryElves = new ArrayList<>();
        while (presents < input) {
            if (house % 50 == 0) {
                exhaustedElves.add(house / 50);
            }
            house++;
            deliveryElves = findDeliveryElves(house);
            presents = addPresents(deliveryElves);
        }

        Collections.sort(deliveryElves);
        Collections.reverse(deliveryElves);
        System.out.println(deliveryElves);
        System.out.println("Done!");
    }

    private static int addPresents(ArrayList<Integer> divisors) {
        int total = 0;
        for (int divisor : divisors) {
            total += divisor;
        }

        return total * 11;
    }

    private static ArrayList<Integer> findDeliveryElves(int n)
    {
        ArrayList<Integer> deliveryElves = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                if (n/i == i)
                    deliveryElves.add(i);

                else {
                    deliveryElves.add(i);
                    deliveryElves.add(n/i);
                }

            }
        }

        for (int i = 0; i < deliveryElves.size(); i++) {
            int deliveryElf = deliveryElves.get(i);
            if (exhaustedElves.contains(deliveryElf)) {
                deliveryElves.remove(Integer.valueOf(deliveryElf));
                i--;
            }
        }

        return deliveryElves;
    }

}
