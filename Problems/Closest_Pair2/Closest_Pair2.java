import java.util.stream.*;
import java.util.Collections.*;
import java.util.concurrent.Executors;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

/**
 * Closest_Pair2
 */
public class Closest_Pair2 {
    public static final int BRUTEFORCE_LIMIT = 50;
    static final ExecutorService executorService = Executors.newWorkStealingPool(2);

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            long startTime = System.nanoTime();

            int n = io.getInt();
            if (n == 0)
                break;

            Float[][] points = new Float[n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    points[i][j] = (float) io.getDouble();
                }
            }
            // All points have been obtained
            List<Integer> indicies = IntStream.rangeClosed(0, n - 1).boxed().collect(Collectors.toList());
            List<Integer> xSorted = indicies.stream().parallel()
                    .sorted((p1, p2) -> Float.compare(points[p1][0], points[p2][0])).collect(Collectors.toList());
            // All things are now sorted

            Points closestPoints = GetClosestPoints(xSorted, points);
            var p1 = closestPoints.p1;
            var p2 = closestPoints.p2;

            if (p1[0] < p2[0]) {
                System.out.println(p1[0] + " " + p1[1] + " " + p2[0] + " " + p2[1]);
            } else {
                System.out.println(p2[0] + " " + p2[1] + " " + p1[0] + " " + p1[1]);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("ms: " + duration / 1000000);
        }
        io.close();
    }

    public static Points GetClosestPoints(List<Integer> xSorted, Float[][] points) {
        if (xSorted.size() <= BRUTEFORCE_LIMIT)
            return Bruteforce(xSorted, points);

        return RecurseX(xSorted, points);
    }

    public static Points RecurseX(List<Integer> xSorted, Float[][] points) {
        int size = xSorted.size();
        if (size <= BRUTEFORCE_LIMIT)
            return Bruteforce(xSorted, points);

        List<Integer> left = xSorted.subList(0, ((size + 1) / 2));
        List<Integer> right = xSorted.subList(((size + 1) / 2), xSorted.size());
        int mid = xSorted.get((size / 2));
        Float[] midPoint = points[mid];

        

        Points leftClosest = RecurseX(left, points);
        Points rightClosest = RecurseX(right, points);

        if (leftClosest.dist < rightClosest.dist) {
            Points stripClosest = CompareStrip(xSorted, points, leftClosest.dist, midPoint[0]);

            if (stripClosest == null) {
                return leftClosest;
            } else if (leftClosest.dist < stripClosest.dist) {
                return leftClosest;
            } else {
                return stripClosest;
            }
        } else {
            Points stripClosest = CompareStrip(xSorted, points, rightClosest.dist, midPoint[0]);

            if (stripClosest == null) {
                return rightClosest;
            } else if (rightClosest.dist < stripClosest.dist) {
                return rightClosest;
            } else {
                return stripClosest;
            }
        }
    }

    public static Points CompareStrip(List<Integer> xSorted, Float[][] points, float d, float mid) {
        Predicate<Integer> closeToMid = p -> Math.abs(points[p][0] - mid) < d;
        List<Integer> ySorted = xSorted.stream().parallel().filter(closeToMid)
                .sorted((p1, p2) -> Float.compare(points[p1][1], points[p2][1])).collect(Collectors.toList());
        float min = Float.MAX_VALUE;
        int size = ySorted.size();
        Points minPoints = new Points();
        if (size < 2)
            return null;
        else if (size == 2) {
            Float[] p1 = points[ySorted.get(0)];
            Float[] p2 = points[ySorted.get(1)];
            float dist = Dist(p1, p2);
            minPoints.SetPoints(p1, p2, dist);
        } else {
            for (int i = 0; i < size; i++) {
                Float[] p1 = points[ySorted.get(i)];
                for (int j = i + 1; j < size; j++) {
                    Float[] p2 = points[ySorted.get(j)];
                    float dist = Dist(p1, p2);
                    if (dist < min) {
                        min = dist;
                        minPoints.SetPoints(p1, p2, dist);
                    }
                }
            }
        }
        return minPoints;
    }

    public static Points Bruteforce(List<Integer> sorted, Float[][] points) {
        float min = Float.MAX_VALUE;
        Points minPoints = new Points();
        int size = sorted.size();
        for (int i = 0; i < size; i++) {
            Float[] p1 = points[sorted.get(i)];
            for (int j = i + 1; j < size; j++) {
                Float[] p2 = points[sorted.get(j)];
                if (i == j)
                    continue;
                float dist = Dist(p1, p2);
                if (dist < min) {
                    min = dist;
                    minPoints.SetPoints(p1, p2, dist);
                }
            }
        }
        return minPoints;
    }

    public static float Dist(Float[] p1, Float[] p2) {
        return (float) Math.sqrt((p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]));
    }
}

class Points {
    public Float[] p1;
    public Float[] p2;
    public Float dist;

    public Points() {

    }

    public void SetPoints(Float[] p1, Float[] p2, Float dist) {
        this.p1 = p1;
        this.p2 = p2;
        this.dist = dist;
    }
}

class Tuple<X, Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
