import java.util.Iterator;
import java.util.List;

public class Itertools {

    /**
     *
     * @param start
     * @param step
     * @return
     */
    public static Iterable<Integer> count(int start, int step){
        return () -> new Iterator<Integer>() {
            int count = start - step;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                count+=step;
                return count;
            }
        };
    }

    public static Iterable<Integer> count(int start){
        return count(start,1);
    }

    public static <T> Iterable<T> cycle(List<T> list){
        return () -> new Iterator<T>() {
            int count = -1;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                count++;
                count = count%list.size();
                return list.get(0);
            }
        };
    }

    /**
     *
     * @param t
     * @param n
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> repeat(T t,int n){
        return () -> new Iterator<T>() {
            int count = -1;
            @Override
            public boolean hasNext() {
                count++;
                return count<n;
            }

            @Override
            public T next() {
                return t;
            }
        };
    }

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> repeat(T t){
        return () -> new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return t;
            }
        };
    }

    /**
     *
     * @param iterables
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> chain(List<T>... iterables) {
        int size = 0;
        for (List<T> list :
                iterables) {
            size += list.size();
        }
        int finalSize = size;
        return () -> new Iterator<T>() {
            int counter = -1;
            int iterableNo = 0;
            int sizeTillNow = 0;

            @Override
            public boolean hasNext() {
                counter++;
                return counter < finalSize;
            }

            @Override
            public T next() {
                int presentIterableIndex = counter - sizeTillNow;
                while (presentIterableIndex >= iterables[iterableNo].size()) {
                    sizeTillNow += iterables[iterableNo].size();
                    presentIterableIndex = counter - sizeTillNow;
                    iterableNo++;
                }
                return iterables[iterableNo].get(presentIterableIndex);
            }
        };
    }

    /**
     *
     * @param data
     * @param selectors
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> compress(List<T> data, List<Boolean> selectors){
        return () -> new Iterator<T>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                index ++;
                while(index < data.size() && !selectors.get(index) )
                    index++;
                return index < data.size();
            }

            @Override
            public T next() {
                return data.get(index);
            }
        };
    }

    /**
     *
     * @param pred
     * @param seq
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> dropWhile(Predicate<T> pred,List<T> seq){
        return () -> new Iterator<T>() {
            boolean next = false;
            int index = -1;
            @Override
            public boolean hasNext() {
                index++;
                while (!next&&index<seq.size()&&pred.pred(seq.get(index))) {
                    index++;
                }
                next = true;
                return index<seq.size();
            }

            @Override
            public T next() {
                return seq.get(index);
            }
        };
    }

    /**
     *
     * @param predicate
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> ifilter(Predicate<T> predicate, List<T> list){
        return () -> new Iterator<T>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                index++;
                while (index<list.size()&& !predicate.pred(list.get(index)))
                    index++;
                return index<list.size();
            }

            @Override
            public T next() {
                return list.get(index);
            }
        };
    }


    public static <T> Iterable<T> ifilterfalse(Predicate<T> predicate, List<T> list){
        return () -> new Iterator<T>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                index++;
                while (index<list.size()&& predicate.pred(list.get(index)))
                    index++;
                return index<list.size();
            }

            @Override
            public T next() {
                return list.get(index);
            }
        };
    }

    public static <T> Iterable<T> islice(List<T> seq, int start, int stop,int step){
        return () -> new Iterator<T>() {
            int index = start -step;
            @Override
            public boolean hasNext() {
                index+=step;
                return index <seq.size()&&index<stop&& index >=start;
            }

            @Override
            public T next() {
                return seq.get(index);
            }
        };
    }
    public static <T> Iterable<T> islice(List<T> seq, int stop,int step){
        return islice(seq,0,stop,step);
    }
    public static <T> Iterable<T> islice(List<T> seq, int stop){
        return islice(seq,0,stop,1);
    }


    public static <T,U> Iterable<U> imap(Function<T,U> function,List<T>... lists){
        return () -> new Iterator<U>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                index++;
                return index<lists[0].size();
            }

            @Override
            public U next() {
                T[] arr = (T[])new Object[lists.length];
                for (int i = 0; i < lists.length; i++) {
                    arr[i] = lists[i].get(index);
                }
                return function.apply(arr);
            }
        };
    }
}
