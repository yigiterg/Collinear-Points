import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int numberOfSegments = 0;
    private final ArrayList <LineSegment> lineSegments = new ArrayList<>();

    public  BruteCollinearPoints(Point[] pointer) {
            if (pointer == null) throw new IllegalArgumentException();
            double slope = 0;
            checkForNull(pointer);
            Arrays.sort(pointer, Point::compareTo);
            checkForDuplicate(pointer);
            for (int i = 0; i < pointer.length; i++) {
                for (int j = i + 1; j < pointer.length; j++) {
                    slope = pointer[i].slopeTo(pointer[j]);
                    for (int k = j + 1; k < pointer.length; k++) {
                        if (pointer[i].slopeTo(pointer[k]) == slope) {
                            for (int z = k + 1; z < pointer.length; z++) {
                                if (pointer[i].slopeTo(pointer[z]) == slope) {
                                    lineSegments.add(new LineSegment(pointer[i], pointer[z]));
                                    numberOfSegments++;
                                }
                            }
                        }
                    }
                }
            }
        }
        // finds all line segments containing 4 points
    public int numberOfSegments()  {
        return numberOfSegments;
    }      // the number of line segments
    public LineSegment[] segments() {
        LineSegment[] segmentArray = new LineSegment[numberOfSegments];
        int i = 0;
        for (LineSegment seg : lineSegments) {
            segmentArray[i] = seg;
            i++;
        }
        return segmentArray;
    }

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
