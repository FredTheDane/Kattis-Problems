import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Closest_Parallel_Pair
 */
public class Closest_Parallel_Pair {
    public static final boolean DEBUG = false;
    public static final int BRUTEFORCE_LIMIT = 3;
    public static final ExecutorService service = Executors.newWorkStealingPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            if (n == 0)
                break;

            List<Point> xSorted = SortByX(GetPoints(io, n));
            long startTime = System.nanoTime();
            Tuple<Float,Pair> closestPoints = RecurseX(xSorted);
            Point p1 = closestPoints.y.P1;
            Point p2 = closestPoints.y.P2;

            if (p1.x < p2.x) {
                io.print(p1 + " " + p2 + "\n");
            } else {
                io.print(p2 + " " + p1 + "\n");
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            if (DEBUG) {
                System.out.println("ms: " + duration / 1000000);
            }
        }
        io.close();
    }

    public static Tuple<Float,Pair> RecurseX(List<Point> points)
            throws InterruptedException, ExecutionException {
        int size = points.size();
        if (size <= BRUTEFORCE_LIMIT)
            return Bruteforce(points);

        Point midPoint = points.get((size / 2));
    
        Future<Tuple<Float, Pair>> f1 = service.submit(new Callable<Tuple<Float, Pair>>() {
            public Tuple<Float, Pair> call() throws Exception {
                List<Point> left = points.subList(0, ((size + 1) / 2));
                return RecurseX(left);
            }
        });

        Future<Tuple<Float, Pair>> f2 = service.submit(new Callable<Tuple<Float, Pair>>() {
            public Tuple<Float, Pair> call() throws Exception {
                List<Point> right = points.subList(((size + 1) / 2), size);
                return RecurseX(right);
            }
        });
        
        Tuple<Float, Pair> leftClosest = f1.get();
        Tuple<Float, Pair> rightClosest = f2.get();
        Tuple<Float, Pair> best;

        if (leftClosest.x < rightClosest.x) {
            best = leftClosest;
        } else {
            best = rightClosest;
        }

        Tuple<Float, Pair> stripClosest = CompareStrip(points, leftClosest.x, midPoint.x);

        if (stripClosest == null) {
            return best;
        } else if (best.x < stripClosest.x) {
            return best;
        } else {
            return stripClosest;
        }
    }

    public static Tuple<Float,Pair> CompareStrip(List<Point> points, float d, float mid) {
        Predicate<Point> closeToMid = p -> Math.abs(p.x - mid) < d;
        List<Point> ySorted = points.stream().filter(closeToMid)
                .sorted((p1, p2) -> Float.compare(p1.y, p2.y)).collect(Collectors.toList());
        float min = Float.MAX_VALUE;
        int size = ySorted.size();
        Pair minPoints = null;
        if (size < 2)
            return null;
        else if (size == 2) {
            Point p1 = ySorted.get(0);
            Point p2 = ySorted.get(1);
            min = p1.Dist(p2);
            minPoints = new Pair(p1, p2);
        } else {
            for (int i = 0; i < size; i++) {
                Point p1 = ySorted.get(i);
                for (int j = i + 1; j < size; j++) {
                    Point p2 = ySorted.get(j);
                    float dist = p1.Dist(p2);
                    if (dist < min) {
                        min = dist;
                        minPoints = new Pair(p1, p2);
                    }
                }
            }
        }
        return new Tuple<Float,Pair>(min, minPoints);
    }

    public static Tuple<Float,Pair> Bruteforce(List<Point> points) {
        float min = Float.MAX_VALUE;
        Pair minPoints = null;
        int size = points.size();
        List<Point> ySorted = points.stream().sorted((p1, p2) -> Float.compare(p1.y, p2.y)).collect(Collectors.toList());
        
        for (int i = 0; i < size; i++) {
            Point p1 = ySorted.get(i);
            for (int j = i + 1; j < size; j++) {
                Point p2 = ySorted.get(j);
                if (i == j)
                    continue;
                float dist = p1.Dist(p2);
                if (dist < min) {
                    min = dist;
                    minPoints = new Pair(p1, p2);
                }
            }
        }
        return new Tuple<Float,Pair>(min, minPoints);
    }

    public static List<Point> GetPoints(Kattio io, int n) {
        ArrayList<Point> points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point((float)io.getDouble(), (float)io.getDouble()));
        }
        return points;
    }

    public static List<Point> SortByX(List<Point> points) {
        return points.stream().sorted((p1, p2) -> Float.compare(p1.x, p2.x)).collect(Collectors.toList());
    }
}