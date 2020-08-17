import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FastCollinearPoints {

    private  LineSegment[] segments;

    public FastCollinearPoints(Point[] pointer) {
        if (pointer == null) throw new IllegalArgumentException();
        Point[] points = Arrays.copyOf(pointer, pointer.length);
        Point[] pointSlope = Arrays.copyOf(pointer, pointer.length);
        checkForNull(points);
        Arrays.sort(points, Point::compareTo);
        checkForDuplicate(points);
        //   ArrayList<Double> endPointSlope = new ArrayList<>();
        //    ArrayList<Point> endPoints = new ArrayList<>();
        ArrayList<LineSegment> segmentList = new ArrayList<>();
        for (int i = 0; i < points.length - 3; i++) {
            //    int count = 0;
            Point origin = points[i];
            Arrays.sort(pointSlope);
            Arrays.sort(pointSlope, origin.slopeOrder());
            Point lineStart = null;
            int count = 1;
            for (int j = 0; j < points.length - 1; j++) {
                if (pointSlope[j].slopeTo(origin) == pointSlope[j + 1].slopeTo(origin)) {
                    count++;
                    if (count == 2) {
                        lineStart = pointSlope[j];
                        count++;
                    } else if (count >= 4 && j == pointSlope.length - 2) {  // if j is the last point in the array we have to count it as well
                        assert lineStart != null;
                        if (lineStart.compareTo(origin) > 0) { //if line start above the origin
                            segmentList.add(new LineSegment(origin, pointSlope[j + 1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4) {
                    assert lineStart != null;
                    if (lineStart.compareTo(origin) > 0) {
                            segmentList.add(new LineSegment(origin, pointSlope[j]));
                        }
                        count = 1;
                }
                else {
                        count = 1;
                    }
                }
            }
            segments = segmentList.toArray(new LineSegment[0]);

        }
    // finds all line segments containing 4 or more points
    public  int numberOfSegments() {
        return segments.length;
    }       // the number of line segments
    public LineSegment[] segments()    {
        return segments;
    }            // the line segments


    private void checkForNull(Point [] points) {
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Null point");
        }
    }

    private void checkForDuplicate(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) throw new IllegalArgumentException("Duplicated point");
        }
    }
}