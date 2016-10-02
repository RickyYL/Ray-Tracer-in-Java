package samplers;

import utilities.Maths;
import utilities.Point2D;
import utilities.RgbColor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
abstract public class Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * The number of sample points in a pattern.
     */
    int numSamples = 1;

    /**
     * The number of sample sets (patterns) stored. The default value is 83, which has nothing
     * special but only a prime.
     */
    int numSets = 83;

    /**
     * The current number of sample points used. It is initialized to zero when a
     * <code>Sampler</code> object is constructed. It is incremented by one each time the
     * method <code>nextSampleOnUnitSquare</code> is called, its value at the end of each function
     * call is the total number of squareSamples returned at the time.
     */
    long count = 0;

    /**
     * Random index jump.
     */
    int jump = 0;

    /**
     * Shuffled squareSamples array indices.
     */
    List<Integer> shuffledIndices = new ArrayList<>();

    /**
     * Sample points on a unit square generated by the method <code>generateSamples()</code>.
     * It's a 1D array but can be accessed using index. For most sampling applications,
     * it's important to use different sets with adjacent pixels. If we don't do this, the
     * results can be aliasing artifacts that are far worse than the jaggies.
     */
    List<Point2D> squareSamples = new ArrayList<>();

    /**
     * Samples mapped from the <code>squareSamples</code> to samples on a disk. Call
     * <code>mapSamplesToUnitDisk</code> first.
     */
    List<Point2D> diskSamples = new ArrayList<>();

    /**
     * Samples mapped from the <code>squareSamples</code> to samples on a disk. Call
     * <code>mapSamplesToHemisphere</code> first.
     */
    List<Point2D> hemisphereSamples = new ArrayList<>();


/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Sampler(int numSamples) {
        this.numSamples = numSamples;
        generateSamples();
    }

    public Sampler(int numSamples, int numSets) {
        this.numSamples = numSamples;
        this.numSets = numSets;
        generateSamples();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Sampler specific methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Generates sample patterns in a unit square, stored in data field <code>squareSamples
     * </code>. Each subclass has to override this method. It doesn't need to be called
     * explicitly but called by the constructor by default.
     */
    abstract public void generateSamples();

    /**
     * TODO
     * Maps sample points in a unit square to a unit disk stored in the
     * <code>diskSamples</code>. It is called when constructing an object that has to supply
     * squareSamples on a disk.
     * This method applies the concentric map developed by Shirley in the early 1990s.
     * <em>Problem</em>: It has to be called after <code>generateSamples</code>. I'll fix
     * that later.
     */
    public void mapSamplesToUnitDisk() {
        int size = squareSamples.size();
        double r, phi;                  // polar coordinate
        Point2D sp = new Point2D();     // sample point on unit
        diskSamples = new ArrayList<>();

        for (int j = 0; j < size; j++) {

            // map the sample point to [-1,1]x[-1,1]
            sp.x = 2.0 * squareSamples.get(j).x - 1.0;
            sp.y = 2.0 * squareSamples.get(j).y - 1.0;

            if (sp.x > -sp.y) {
                if (sp.x > sp.y) {
                    r = sp.x;
                    phi = sp.y / sp.x;
                } else {
                    r = sp.y;
                    phi = 2 - sp.x / sp.y;
                }
            } else {
                if (sp.x < sp.y) {
                    r = -sp.x;
                    phi = 4 + sp.y / sp.x;
                } else {
                    r = -sp.y;
                    if (sp.y != 0.0)
                        phi = 6 - sp.x / sp.y;
                    else
                        phi = 0.0;
                }
            }

            phi *= Math.PI / 4.0;

            diskSamples.set(j, new Point2D(r * Math.cos(phi), r * Math.sin(phi)));
        }
    }


    public void mapSamplesToHemisphere(double e) {

    }

    /**
     * Returns sample points in a unit square. It is called from objects that need to
     * supply sample points on squares. For example, the view plane will do this for every
     * scene.
     * @return the next sample point stored in the sampler object.
     */
    public Point2D nextSampleOnUnitSquare() {
//        if (count % numSamples == 0)
//            jump = Maths.randInt() % numSets;
//        return squareSamples.get(
//                jump + shuffledIndices.get(jump + (int)count++ % numSamples));
        return squareSamples.get((int) count++ % numSamples);
    }

    /**
     * Returns sample points in a unit disk. It is caller's responsibility to first call
     * <code>mapSamplesToUnitDisk</code>.
     * @return the next sample point in a unit disk stored in the sampler object.
     */
    public Point2D nextSampleOnUnitDisk() {
        if (count % numSamples == 0)
            jump = Maths.randInt() % numSets;
        return diskSamples.get(
                jump + shuffledIndices.get(jump + (int)count++ % numSamples));
    }

    /**
     * Returns sample points in a hemisphere. It is caller's responsibility to first call
     * <code>mapSamplesToHemisphere</code>.
     * @return the next sample point in a unit disk stored in the sampler object.
     */
    public Point2D nextSampleOnHemisphere() {
        if (count % numSamples == 0)
            jump = Maths.randInt() % numSets;
        return hemisphereSamples.get(
                jump + shuffledIndices.get(jump + (int)count++ % numSamples));
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Helper methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Sets up the randomly shuffled indices.
     */
    public void setupShuffledIndices() {
        List<Integer> indices =
                IntStream.range(0, numSamples).boxed().collect(Collectors.toList());
        for (int i = 0; i < numSets; i++) {
            Collections.shuffle(indices);       // a set of shuffled indices
            shuffledIndices.addAll(indices.subList(0, numSamples));
        }
    }

    /**
     * Randomly shuffle the squareSamples in each patterns.
     */
    public void shuffleSamples() {

    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public int getNumSamples() {
        return numSamples;
    }

    public void setNumSamples(int numSamples) {
        this.numSamples = numSamples;
    }

    public int getNumSets() {
        return numSets;
    }

    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public List<Point2D> getSquareSamples() {
        return squareSamples;
    }

    public void setSquareSamples(List<Point2D> squareSamples) {
        this.squareSamples = squareSamples;
    }

    public List<Integer> getShuffledIndices() {
        return shuffledIndices;
    }

    public void setShuffledIndices(List<Integer> shuffledIndices) {
        this.shuffledIndices = shuffledIndices;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Tests
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * A useful test method to display sample points in a unit square.
     * @param args input arguments
     */
    public static void main(String[] args) {

        int size = 500;
        Sampler sampler = new Hammersley(64, 1);
        BufferedImage image = new BufferedImage(size + 50, size + 50, BufferedImage.TYPE_INT_RGB);

        // draw samples and their projections
        sampler.squareSamples.forEach(e -> {
            image.setRGB((int) (e.x * size), (int) (e.y * size), RgbColor.WHITE.toInt());
            image.setRGB((int) (e.x * size), size + 25, RgbColor.RED.toInt());
            image.setRGB(size + 25, (int) (e.y * size), RgbColor.RED.toInt());
        });

        // draw lines
        Graphics gr = image.getGraphics();
        gr.drawLine(0, size, size, size);
        gr.drawLine(size, 0, size, size);

        // open a new window and display
        JFrame frame = new JFrame("Samples");
        frame.getContentPane().add(new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(image, 50, 50, this);
            }
        });
        frame.setSize(size + 150, size + 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
