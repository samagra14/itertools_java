import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Itertools {

    /**
     * @param start
     * @param step
     * @return
     */
    public static Iterable<Integer> count(int start, int step) {
        return () -> new Iterator<Integer>() {
            int count = start - step;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                count += step;
                return count;
            }
        };
    }

    public static Iterable<Integer> count(int start) {
        return count(start, 1);
    }

       public static <T> Iterable<T> cycle(Iterable<T> list) {
        return () -> new Iterator<T>() {
            Iterator<T> iterator = list.iterator();

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                try {
                    return (T) iterator.next();
                } catch (Exception e) {
                    iterator = list.iterator();
                    return (T) iterator.next();
                }
            }
        };
    }
    /**
     * @param t
     * @param n
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> repeat(T t, int n) {
        return () -> new Iterator<T>() {
            int count = -1;

            @Override
            public boolean hasNext() {
                count++;
                return count < n;
            }

            @Override
            public T next() {
                return t;
            }
        };
    }

    /**
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Iterable<T> repeat(T t) {
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
     * @param iterables
     * @param           <T>
     * @return
     */
    public static <T> Iterable<T> chain(Iterable<T>... iterables) {
        int no_of_iterables = iterables.length;
        return () -> new Iterator<T>() {
            int iterableNo = 0;
            Iterator<T> iterator = iterables[iterableNo].iterator();

            @Override
            public boolean hasNext() {
                if (iterableNo == no_of_iterables - 1) {
                    return iterator.hasNext();
                }
                return true;
            }

            @Override
            public T next() {
                if (iterator.hasNext()) {
                    return (T) iterator.next();
                } else {
                    while (!iterator.hasNext()) {
                        iterableNo++;
                        iterator = iterables[iterableNo].iterator();
                    }
                    return (T) iterator.next();
                }
            }
        };
    }
    
       /**
     * @param data
     * @param selectors
     * @param           <T>
     * @return
     */
    public static <T> Iterable<T> compress(Iterable<T> data, List<Boolean> selectors) {
        Iterator<T> iterator = data.iterator();
        return () -> new Iterator<T>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                index++;
                while (index < selectors.size() && !selectors.get(index)) {
                    index++;
                    iterator.next();
                }
                return index < selectors.size();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }

      /**
     * @param pred
     * @param seq
     * @param      <T>
     * @return
     */
    public static <T> Iterable<T> dropWhile(Predicate<T> pred, Iterable<T> seq) {
        Iterator<T> iterator = seq.iterator();
        return () -> new Iterator<T>() {
            boolean next = false;
            int index = -1;
            boolean firstElement = true;
            T obj = iterator.next();

            @Override
            public boolean hasNext() {
                index++;
                while (!next && pred.pred(obj)) {
                    obj = iterator.next();
                    index++;
                }
                next = true;
                return iterator.hasNext();
            }

            @Override
            public T next() {
                if (firstElement) {
                    firstElement = false;
                    return obj;
                }
                return iterator.next();
            }
        };
    }

      /**
     * @param predicate
     * @param list
     * @param           <T>
     * @return
     */
    public static <T> Iterable<T> ifilter(Predicate<T> predicate, Iterable<T> list) {
        Iterator<T> iterator = list.iterator();
        return () -> new Iterator<T>() {
            T obj;
            boolean nex;
            @Override
            public boolean hasNext() {
                nex=iterator.hasNext();
                if(nex)
                obj = iterator.next();
                if (!predicate.pred(obj)) {
                    for (int i = 0; ; i++) {
                        nex=iterator.hasNext();
                        if(!nex)
                            return false;
                        obj=iterator.next();
                        if (predicate.pred(obj))
                        return nex;
                    }
                }
                return nex;
            }

            @Override
            public T next() {
                return obj;
            }
        };
    }

    public static <T> Iterable<T> ifilterfalse(Predicate<T> predicate, List<T> list) {
        return () -> new Iterator<T>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                index++;
                while (index < list.size() && predicate.pred(list.get(index)))
                    index++;
                return index < list.size();
            }

            @Override
            public T next() {
                return list.get(index);
            }
        };
    }

    public static <T> Iterable<T> islice(List<T> seq, int start, int stop, int step) {
        return () -> new Iterator<T>() {
            int index = start - step;

            @Override
            public boolean hasNext() {
                index += step;
                return index < seq.size() && index < stop && index >= start;
            }

            @Override
            public T next() {
                return seq.get(index);
            }
        };
    }

    public static <T> Iterable<T> islice(List<T> seq, int stop, int step) {
        return islice(seq, 0, stop, step);
    }

    public static <T> Iterable<T> islice(List<T> seq, int stop) {
        return islice(seq, 0, stop, 1);
    }


    public static <T, U> Iterable<U> imap(Function<T, U> function, List<T>... lists) {
        return () -> new Iterator<U>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                index++;
                return index < lists[0].size();
            }

            @Override
            public U next() {
                List<T> list = new ArrayList<>();
                for (int i = 0; i < lists.length; i++) {
                    list.add(lists[i].get(index));
                }
                return function.apply(list);
            }
        };
    }

        public static <T> Iterable<T> takeWhile(Predicate<T> pred, Iterable<T> seq) {
        Iterator<T> iterator = seq.iterator();
        return () -> new Iterator<T>() {
            T obj = iterator.next();
            boolean firstElement = true;

            @Override
            public boolean hasNext() {
                if (pred.pred(obj)) {
                    if (firstElement)
                        firstElement = false;
                    else
                        obj = iterator.next();
                    return iterator.hasNext() && pred.pred(obj);
                }
                return false;
            }

            @Override
            public T next() {
                return obj;
            }
        };
    }

    public static <T> Iterable<List<T>> izip(List<T>... lists) {
        int smallest = lists[0].size();
        for (List<T> list :
                lists) {
            if (list.size() < smallest)
                smallest = list.size();
        }
        int finalSmallest = smallest;
        return () -> new Iterator<List<T>>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                index++;
                return index < finalSmallest;
            }

            @Override
            public List<T> next() {
                List<T> temp = new ArrayList<>();
                for (List<T> list :
                        lists) {
                    temp.add(list.get(index));
                }
                return temp;
            }
        };
    }


    public static <T> Iterable<List<T>> izipLongest(T fillValue, List<T>... lists) {
        int largest = lists[0].size();
        for (List<T> list :
                lists) {
            if (list.size() > largest)
                largest = list.size();
        }
        int finalLargest = largest;
        return () -> new Iterator<List<T>>() {
            int index = -1;

            @Override
            public boolean hasNext() {
                index++;
                return index < finalLargest;
            }

            @Override
            public List<T> next() {
                List<T> temp = new ArrayList<>();
                for (List<T> list :
                        lists) {
                    if (index < list.size())
                        temp.add(list.get(index));
                    else
                        temp.add(fillValue);
                }
                return temp;
            }
        };
    }

    public static <T> Iterable<List<T>> product(List<T>... lists) {
        int total = 1;
        int[] max = new int[lists.length];
        for (int i = 0; i < lists.length; i++) {
            max[i] = lists[i].size();
        }
        int[] initProduct = new int[lists.length];
        Arrays.fill(initProduct, 1);
        for (List<T> list :
                lists) {
            total *= list.size();
        }
        int finalTotal = total;
        return () -> new Iterator<List<T>>() {
            int index = -1;
            int[] presentProduct;

            @Override
            public boolean hasNext() {
                index++;
                return index < finalTotal;
            }

            @Override
            public List<T> next() {
                if (index == 0)
                    presentProduct = initProduct;
                else
                    presentProduct = PermutationGenerator.generateNextProduct(presentProduct, max);
                List<T> result = new ArrayList<>();
                for (int i = 0; i < presentProduct.length; i++) {
                    result.add(lists[i].get(presentProduct[i] - 1));
                }
                return result;
            }
        };
    }

    public static <T> Iterable<List<T>> combinations(List<T> list, int r) {
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return new Iterator<List<T>>() {
                    int index = -1;
                    int total = (int) CombinationGenerator.nCr(list.size(), r);
                    int[] currCombination = new int[r];

                    @Override
                    public boolean hasNext() {
                        index++;
                        return index < total;
                    }

                    @Override
                    public List<T> next() {
                        if (index == 0) {
                            for (int i = 0; i < currCombination.length; i++) {
                                currCombination[i] = i + 1;
                            }
                        } else
                            currCombination = CombinationGenerator.generateNextCombination(currCombination, list.size(), r);
                        List<T> result = new ArrayList<>();
                        for (int aCurrCombination : currCombination) {
                            result.add(list.get(aCurrCombination - 1));
                        }
                        return result;
                    }
                };
            }
        };
    }

    public static <T> Iterable<List<T>> permutations(List<T> list, int r) {
        long rfact = (long) PermutationGenerator.factorial(r);
        long total = (long) CombinationGenerator.nCr(list.size(), r) * rfact;
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return new Iterator<List<T>>() {
                    int index = -1;
                    int permNo = 0;
                    int[] currPermutation = new int[r];
                    int[] currCombination = new int[r];

                    @Override
                    public boolean hasNext() {
                        index++;
                        return index < total;
                    }

                    @Override
                    public List<T> next() {
                        if (index == 0) {
                            permNo = 0;
                            for (int i = 0; i < currCombination.length; i++) {
                                currCombination[i] = i + 1;
                                currPermutation[i] = i + 1;
                            }

                        } else if (((permNo + 1) % rfact) == 0) {
                            permNo++;
                            currCombination = CombinationGenerator.generateNextCombination(currCombination, list.size(), r);
                            for (int i = 0; i < currCombination.length; i++) {
                                currPermutation[i] = i + 1;
                            }
                        } else {
                            permNo++;
                            currPermutation = PermutationGenerator.generateNextPermutation(currPermutation, r);
                        }
                        List<T> result = new ArrayList<>();
                        for (int i = 0; i < r; i++) {
                            result.add(list.get(currCombination[currPermutation[i] - 1] - 1));
                        }
                        return result;
                    }
                };
            }
        };
    }
}
