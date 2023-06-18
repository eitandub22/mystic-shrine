package BusinessLayer.DataStructs;

import java.util.Comparator;
import java.util.TreeSet;

public class FindTreeSet<E> extends TreeSet<E> {
    public FindTreeSet(Comparator<E> comperator)
    {
        super(comperator);
    }

    public E find(E item)
    {
        E toRet = this.ceiling(item);
        return toRet;
    }
}
