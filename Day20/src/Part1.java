import java.util.ArrayList;
import java.util.Collections;

public class Part1 {
    private static final int input = 33100000;

    public static void main(String[] args) {

        int house = 0;
        int presents = 0;
        ArrayList<Integer> divisors = new ArrayList<>();
        while (presents < input) {
            house++;
            divisors = findDivisors(house);
            presents = addDivisors(divisors) * 10;
        }

        Collections.sort(divisors);
        Collections.reverse(divisors);
        System.out.println(divisors);
        System.out.println("Done!");
    }

    private static int addDivisors(ArrayList<Integer> divisors) {
        int total = 0;
        for (int divisor : divisors) {
            total += divisor;
        }

        return total;
    }

    static ArrayList<Integer> findDivisors(int n)
    {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                if (n/i == i)
                    divisors.add(i);

                else {
                    divisors.add(i);
                    divisors.add(n/i);
                }

            }
        }
        return divisors;
    }

}
