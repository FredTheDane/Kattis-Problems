import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Closest_Parallel_Pair
 */
public class Closest_Parallel_Pair {
    public static final boolean DEBUG = true;
    public static final int BRUTEFORCE_LIMIT = 20;
    public static final ExecutorService service = Executors.newWorkStealingPool(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            if (n == 0)
                break;

            Point[] xSorted = SortByX(GetPoints(io, n));
            long startTime = System.nanoTime();
            Tuple<Float,Pair> closestPoints = GetClosestPoints(xSorted);
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

    public static Tuple<Float,Pair> GetClosestPoints(Point[] points)
            throws InterruptedException, ExecutionException {
        if (points.length <= BRUTEFORCE_LIMIT)
            return Bruteforce(points);

        return RecurseX(points);
    }

    public static Tuple<Float,Pair> RecurseX(Point[] points)
            throws InterruptedException, ExecutionException {
        int size = points.length;
        if (size <= BRUTEFORCE_LIMIT)
            return Bruteforce(points);

        Point midPoint = points[(size / 2)];

        Future<Tuple<Float, Pair>> leftClosestFuture = service.submit(new Callable<Tuple<Float, Pair>>() {
            public Tuple<Float, Pair> call() throws Exception {
                Point[] left = Arrays.copyOfRange(points, 0, ((size + 1) / 2));
                return RecurseX(left);
            }
        });

        Future<Tuple<Float, Pair>> rightClosestFuture = service.submit(new Callable<Tuple<Float, Pair>>() {
            public Tuple<Float, Pair> call() throws Exception {
                Point[] right = Arrays.copyOfRange(points, ((size + 1) / 2), size);
                return RecurseX(right);
            }
        });

        Tuple<Float, Pair> leftClosest = leftClosestFuture.get();
        Tuple<Float, Pair> rightClosest = rightClosestFuture.get();
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

    public static Tuple<Float,Pair> CompareStrip(Point[] points, float d, float mid) {
        Predicate<Point> closeToMid = p -> Math.abs(p.x - mid) < d;
        Point[] ySorted = Arrays.stream(points).filter(closeToMid)
                .sorted((p1, p2) -> Float.compare(p1.y, p2.y)).toArray(Point[]::new);
        float min = Float.MAX_VALUE;
        int size = ySorted.length;
        Pair minPoints = null;
        if (size < 2)
            return null;
        else if (size == 2) {
            Point p1 = ySorted[0];
            Point p2 = ySorted[1];
            min = p1.Dist(p2);
            minPoints = new Pair(p1, p2);
        } else {
            for (int i = 0; i < size; i++) {
                Point p1 = ySorted[i];
                for (int j = i + 1; j < Math.min(size, 4); j++) {
                    Point p2 = ySorted[j];
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

    public static Tuple<Float,Pair> Bruteforce(Point[] points) {
        float min = Float.MAX_VALUE;
        Pair minPoints = null;
        int size = points.length;
        Point[] ySorted = Arrays.stream(points).sorted((p1, p2) -> Float.compare(p1.y, p2.y)).toArray(Point[]::new);
        
        for (int i = 0; i < size; i++) {
            Point p1 = ySorted[i];
            for (int j = i + 1; j < Math.min(size, 4); j++) {
                Point p2 = ySorted[j];
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

    public static Point[] GetPoints(Kattio io, int n) {
        long startTime = System.nanoTime();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point((float)io.getDouble(), (float)io.getDouble());
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        if (DEBUG) {
            System.out.println("Points parsing ms: " + duration / 1000000);
        }
        return points;
    }

    public static Point[] SortByX(Point[] points) {
        return Arrays.stream(points).sorted((p1, p2) -> Float.compare(p1.x, p2.x))
                    .toArray(Point[]::new);
    }
}