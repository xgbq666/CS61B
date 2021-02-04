package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(44.0);
        arb.enqueue(21.2);
        arb.enqueue(34.5);
        arb.enqueue(435.32);
        arb.enqueue(2.3);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(21.2);
        for (double s : arb) {
            System.out.println(s);
        }
    }
}
