package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opennum;
    private int size;
    private Boolean[][] plist;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        opennum = 0;
        plist = new Boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N);
        for (int row = 0; row < size; row += 1) {
            for (int col = 0; col < size; col += 1) {
                plist[row][col] = false;
            }
        }
    }

    private void connectAroundOpen(int row, int col) {
        int nid = xyTo1d(row, col);
        if (row - 1 >= 0) {
            if (plist[row - 1][col]) {
                uf.union(xyTo1d(row - 1, col), nid);
            }
        }
        if (row + 1 <= size - 1) {
            if (plist[row + 1][col]) {
                uf.union(xyTo1d(row + 1, col), nid);
            }
        }
        if (col - 1 >= 0) {
            if (plist[row][col - 1]) {
                uf.union(xyTo1d(row, col - 1), nid);
            }
        }
        if (col + 1 <= size - 1) {
            if (plist[row][col + 1]) {
                uf.union(xyTo1d(row, col + 1), nid);
            }
        }
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        plist[row][col] = true;
        int id;
        connectAroundOpen(row, col);
        opennum += 1;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return plist[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        int xy1d = xyTo1d(row, col);
        for (int i = 0; i < size; i += 1) {
            if (!isOpen(0, i)) {
                continue;
            }
            if (uf.connected(xy1d, i)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return opennum;
    }

    public boolean percolates() {
        for (int i = 0; i < size; i += 1) {
            if (!isOpen(size - 1, i)) {
                continue;
            }
            if (isFull(size - 1, i)) {
                return true;
            }
        }
        return false;
    }

    private int xyTo1d(int row, int col) {
        return row * size + col;
    }

    public static void main(String[] args) {

    }
}
