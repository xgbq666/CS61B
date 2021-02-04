import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestUnionFind {
    @Test
    public void testConnect() {
        UnionFind unionFind = new UnionFind(6);
        unionFind.connect(0, 4);
        unionFind.connect(1, 3);
        unionFind.connect(3, 4);
        assertTrue(unionFind.isConnected(0, 3));
    }
}
