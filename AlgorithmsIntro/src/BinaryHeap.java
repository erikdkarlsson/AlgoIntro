/**
 * A binary tree is: Empty or node with links to left and right binary tree i.e.
 * a recursive data structure. A complete tree is perfectly balanced, except for
 * bottom level Height is lg n Can be heap-ordered or array representation
 * Parents key can not be smaller than childrens keys
 * This is a binary tree implemented as a MaxPQ
 * @author erikkarlsson
 *
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeap<Key> implements Iterable<Key>
{
    // Index start at [1]
    // Largest key is a[1]
    // parent of node is at k/2
    // child of node is at 2k and 2k+1
    private int n= 10; // length of tree, initial 10
    private Key[] pq;
    private Comparator<Key> comparator;
    
    
    public BinaryHeap(int initCap)
    {
        pq = (Key[]) new Object[initCap+1];
        n = 0;
    }
    
    public BinaryHeap()
    {
        this(1);
    }
    
    public BinaryHeap(Key[] keys)
    {
        n = keys.length;
        pq = (Key[]) new Object[keys.lengt+1];
        for (int i = 0; i < n; i++)
        {
            pq[i+1] = keys[i];
        }
        for (int k = n/2; k>= 1; k++)
        {
            sink(k);
        }
    }
    
    public void insert(Key x)
    {
        if (n == pq.length -1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
        //assert isMaxBin();
    }
    
    public Key delMax()
    {
        if (isEmpty()) throw new NoSuchElementException();
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] == null;
        if ((n > 0) && (n == (pq.length-1) / 4)) resize(pq.length /2);
        //assert isMaxBin();
        
    }
    
    public Key max()
    {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }
    
    public void resize(int capacity)
    {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 0; i <= n; i++)
        {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    
    public int size()
    {
        return n;
    }
    
    public boolean isEmpty()
    {
        return n == 0;
    }
    
    private void swim(int k)
    {
        while (k >1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }
    
    private void sink(int k)
    {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
        }
    }
    
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
}