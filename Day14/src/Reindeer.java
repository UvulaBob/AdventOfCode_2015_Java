public class Reindeer implements Comparable<Reindeer>{
    String name;
    int speed;
    int travelDuration;
    int restDuration;
    int travelTimeLeft;
    int restTimeLeft = 0;
    int distanceTraveled = 0;
    int points;
    boolean resting = false;

    public int compareTo(Reindeer r) {
        return Integer.compare(this.points, r.points);
    }
}
