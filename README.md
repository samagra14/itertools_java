# <div align="center">Itertools-java</div>
  <div align="center">A library for efficiently generating and iterating over various combinatorial constructs in java.</div><br>
 
<div align="center">
	<a href="https://travis-ci.org/samagra14/itertools_java/builds/">
    <img src="https://travis-ci.org/samagra14/itertools_java.svg?branch=master" />
  </a>
  
[![GitHub issues](https://img.shields.io/github/issues/samagra14/itertools_java.svg)](https://github.com/samagra14/itertools_java/issues)

<a href="https://android-arsenal.com/api?level=15">
<img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat-square"
      alt="API" />
  </a>
	<a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-red.svg?style=flat-square"
      alt="License: MIT" />
  </a>
</div><br>

The aim of this library is to provide an interface
 similar to `itertools` in python.This module implements
 a number of iterator building blocks inspired by
  constructs from APL, Haskell, SML and Python. Each has
   been recast in a form suitable for Java.
   
  For instance, SML provides a tabulation tool:
   tabulate(f) which produces a sequence f(0), f(1),
    .... The same effect can be achieved using this library
    by combining `Itertools.imap()` and `Itertools.count()` to form `Itertools.imap(f, Itertools.count())`.
 
 # Table of Contents
 - [Itertools for java](#itertools-for-java)
 - [Features](#features)
 - [Documentation](#documentation)
   * [Infinite Iterators](#infinite-iterators)
   * [Iterators terminating on the shortest input sequence:](#iterators-terminating-on-the-shortest-input-sequence-)
   * [Combinatoric generators](#combinatoric-generators)
 - [How to use ?](#how-to-use--)
   * [Itertool functions and their usage examples](#itertool-functions-and-their-usage-examples)
     + [ chain(List<T>... iterables)](#1--itertoolschain-list-t--iterables--)
     + [ compress(List<T> data, List<Boolean> selectors)](#2--itertoolscompress-list-t--data--list-boolean--selectors--)
     + [ dropWhile(Predicate<T> pred,List<T> seq)](#3--itertoolsdropwhile-predicate-t--pred-list-t--seq--)
     + [ ifilter(Predicate<T> predicate, List<T> list)](#4--itertoolsifilter-predicate-t--predicate--list-t--list--)
     + [ ifilterfalse(Predicate<T> predicate, List<T> list)](#5--itertoolsifilterfalse-predicate-t--predicate--list-t--list--)
     + [ islice(List<T> seq, int start, int stop,int step)](#6--itertoolsislice-list-t--seq--int-start--int-stop-int-step--)
     + [ imap(Function<T,U> function,List<T>... lists)](#7--itertoolsimap-function-t-u--function-list-t--lists--)
     + [ takeWhile(Predicate<T> pred,List<T> seq)](#8--itertoolstakewhile-predicate-t--pred-list-t--seq--)
     + [ izip(List<T> ... lists)](#8--itertoolsizip-list-t---lists--)
     + [ izipLongest(T fillValue,List<T> ... lists)](#9--itertoolsiziplongest-t-fillvalue-list-t---lists--)
     + [ product(List<T> ... lists)](#10--itertoolsproduct-list-t---lists--)
     + [ combinations(List<T> list, int r)](#11--itertoolscombinations-list-t--list--int-r--)
     + [ permutations(List<T> list,int r)](#12--itertoolspermutations-list-t--list-int-r--)
 - [References](#references)


 
 # Features                                
**This library uses generator like constructs to provide iterators and hence
has no memory and speed limitations as a particular element is generated only when it is needed.**

The following methods have been implemented in the library and are ready for use.
# Documentation
## Infinite Iterators
| Iterator        | Arguments  | Results  | Example |
| ------------- |:-------------:| -----:|  -----:|
| `count()`     | start,[step] | start, start+step , start+2*step,... | count(10) --> 10 11 12 13 14 ...|
| `cycle()`      | p      |   p0, p1, … plast, p0, p1, … |cycle('ABCD') --> A B C D A B C D ...|
| `repeat()` | elem [,n]      |    elem, elem, elem, … endlessly or up to n times | repeat(10, 3) --> 10 10 10|

## Iterators terminating on the shortest input sequence:
| Iterator        | Arguments  | Results  | Example |
| :-------------: |:-------------| :-----|  :-----|
| `chain()`     | p, q, …        |p0, p1, … plast, q0, q1, …| chain('ABC', 'DEF') --> A B C D E F|
| `compress()`  |data, selectors | (d[0] if s[0]), (d[1] if s[1]), …| compress('ABCDEF', [1,0,1,0,1,1]) --> A C E F|
| `dropwhile()` | pred, seq      |seq[n], seq[n+1], starting when pred fails | dropwhile( x-> x<5, [1,4,6,4,1]) --> 6 4 1|
| `ifilter()`   | pred, seq      | elements of seq where pred(elem) is true | ifilter( x-> x%2, Arrays.asList(1,2,3...10) --> 1 3 5 7 9|
| `ifilterfalse`| pred, seq     | elements of seq where pred(elem) is false| ifilterfalse( x-> x%2, Arrays.asList(1,2,3...10) --> 2 4 6 8 10|
| `islice()`    | seq, [start,] stop [, step] | elements from seq[start:stop:step]| islice('ABCDEFG', 2, None) --> C D E F G |
| `imap()`      | func, p, q, …| func(p0, q0), func(p1, q1), …| imap(t -> Math.pow(t[0],t[1]), (2,3,10), (5,2,3)) --> 32 9 1000|
| `takewhile()` | pred, seq     | seq[0], seq[1], until pred fails	| takewhile(x-> x<5, [1,4,6,4,1]) --> 1 4
| `izip()`      | p, q, …       | (p[0], q[0]), (p[1], q[1]), …| izip('ABCD', 'xy') --> Ax By|
| `izipLongest()` | p, q, …     | (p[0], q[0]), (p[1], q[1]), …| izip_longest('ABCD', 'xy', fillvalue='-') --> Ax By C- D- |

## Combinatoric generators
| Iterator        | Arguments  | Results  |
| :-------------: |:-------------| :-----|
| `product()`   | p, q, r …   | cartesian product, equivalent to a nested for-loop |
| `permutations()` | p,r    | r-length tuples, all possible orderings|
| combinations() | p,r    | r-length tuples, in sorted order|
| product("ABCD", "EFGH") | |AE AF AG AH BE BF BG BH CE CF CG CH DE DF DG DH|
| permutations('ABCD', 2)|  | AB AC AD BA BC BD CA CB CD DA DB DC|
| combinations('ABCD', 2) | | AB AC AD BC BD CD|

# How to use ?
1. Include [jar file](https://github.com/samagra14/itertools_java/releases/download/0.01/combinatorial_lib.jar) from [releases](https://github.com/samagra14/itertools_java/releases).
2. Use them as shown below.

## Itertool functions and their usage examples
### 1. `Itertools.chain(List<T>... iterables)`
Make an iterator that returns elements from the first iterable until it is exhausted, then proceeds to the next iterable, until all of the iterables are exhausted. Used for treating consecutive sequences as a single sequence.
````java
public void chainExample() {
        String[] strings = {"Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE"};
        int i = 0;
        for (String s :
                Itertools.chain(Arrays.asList(strings[0], strings[1],
                        strings[2]), Arrays.asList(), Arrays.asList(
                        strings[3], strings[4], strings[5], strings[6]
                ))) {
            assertEquals(strings[i], s);
            i++;
        }
````
### 2. `Itertools.compress(List<T> data, List<Boolean> selectors)`
Make an iterator that filters elements from data returning only those that have a corresponding element in selectors that evaluates to True. Stops when either the data or selectors iterables has been exhausted. 
````java
public void compressExample() {
        String[] data = {"Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE"};
        Boolean[] selector = {false, true, true, false, false, false, true};
        int i = 0;
        for (String s :
                Itertools.compress(Arrays.asList(data), Arrays.asList(selector))) {
            if (i == 0)
                assertEquals("World", s);
            if (i == 1)
                assertEquals("Samagra", s);
            if (i == 2)
                assertEquals("PSPACE", s);
            i++;
        }
    }
````
### 3. `Itertools.dropWhile(Predicate<T> pred,List<T> seq)`
Make an iterator that drops elements from the iterable as long as the predicate is true; afterwards, returns every element. Note, the iterator does not produce any output until the predicate first becomes false, so it may have a lengthy start-up time.
````java
public void dropwhileExample() {
        int i = 0;
        for (int a :
                Itertools.dropWhile(integer -> integer < 5, new ArrayList<>(Arrays.asList(1, 4, 6, 4, 1)))) {
            if (i == 0)
                assertEquals(6, a);
            if (i == 1)
                assertEquals(4, a);
            if (i == 2)
                assertEquals(1, a);
            i++;
        }
    }
````
### 4. `Itertools.ifilter(Predicate<T> predicate, List<T> list)`
Make an iterator that filters elements from iterable returning only those for which the predicate is True.
````java
public void ifilterTest() {
        int i = 0;
        for (int a :
                Itertools.ifilter(integer -> (integer % 2) != 0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))) {
            if (i == 0) {
                assertEquals(1, a);
            }
            if (i == 1) {
                assertEquals(3, a);
            }
            if (i == 2) {
                assertEquals(5, a);
            }
            if (i == 3) {
                assertEquals(7, a);
            }
            if (i == 4) {
                assertEquals(9, a);
            }
            i++;
        }
    }
````

### 5. `Itertools.ifilterfalse(Predicate<T> predicate, List<T> list)`

Make an iterator that filters elements from iterable returning only those for which the predicate is False.

````java
 public void ifilterfalseExample() {
        int i = 0;
        for (int a :
                Itertools.ifilterfalse(integer -> (integer % 2) != 0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))) {
            if (i == 0) {
                assertEquals(2, a);
            }
            if (i == 1) {
                assertEquals(4, a);
            }
            if (i == 2) {
                assertEquals(6, a);
            }
            if (i == 3) {
                assertEquals(8, a);
            }
            if (i == 4) {
                assertEquals(9, a);
            }
            i++;
        }
    }
````
### 6. `Itertools.islice(List<T> seq, int start, int stop,int step)`

Make an iterator that returns selected elements from the iterable. If start is non-zero, then elements from the iterable are skipped until start is reached. Afterward, elements are returned consecutively unless step is set higher than one which results in items being skipped. If stop is None, then iteration continues until the iterator is exhausted, if at all; otherwise, it stops at the specified position. 
````java
 public void isliceExample(){
        int i = 0;
        for (int a :
                Itertools.islice(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2)) {
            if (i == 0)
                assertEquals(1, a);
            if (i == 1)
                assertEquals(3, a);
            if (i == 2)
                assertEquals(5, a);
            assertTrue(i < 3);
            i++;
        }
    }
````
### 7. `Itertools.imap(Function<T,U> function,List<T>... lists)`
Make an iterator that computes the function using arguments from each of the iterables.
````java
public void imapExample() {
        int i = 0;
        for (double a :
                       Itertools.imap((t) -> Math.pow(t.get(0), t.get(1)), Arrays.asList(2, 3, 10), Arrays.asList(5, 2, 3))) {
            if (i == 0)
                assertEquals(32, (int) a);
            if (i == 1)
                assertEquals(9, (int) a);
            if (i == 2) {
                assertEquals(1000, (int) a);
            }
            i++;
        }
    }
````
### 8. `Itertools.takeWhile(Predicate<T> pred,List<T> seq)`
Make an iterator that returns elements from the iterable as long as the predicate is true. 
````java
public void takewhileTest() {
        int i = 0;
        for (int a :
                Itertools.takeWhile(integer -> integer < 5, new ArrayList<>(Arrays.asList(1, 4, 6, 4, 1)))) {
            if (i == 0)
                assertEquals(1, a);
            if (i == 1)
                assertEquals(4, a);
            i++;
        }
    }
````
### 9. `Itertools.izip(List<T> ... lists)`
Make an iterator that aggregates elements from each of the iterables.
````java
public void izipTest(){
        String[] listOne = {"A","B","C","D"};
        String[] listTwo = {"x","y"};
        int i = 0;
        for (List<String> list :
                Itertools.izip(Arrays.asList(listOne),Arrays.asList(listTwo))) {
            assertTrue(i<2);
            assertEquals(listOne[i],list.get(0));
            assertEquals(listTwo[i],list.get(1));
            i++;
        }
    }
````
### 10. `Itertools.izipLongest(T fillValue,List<T> ... lists)`
Make an iterator that aggregates elements from each of the iterables. If the iterables are of uneven length, missing values are filled-in with fillvalue. Iteration continues until the longest iterable is exhausted.
````java
public void izipLongestExample(){
        String[] listOne = {"A","B","C","D"};
        String[] listTwo = {"x","y"};
        int i = 0;
        for (List<String> list :
                Itertools.izipLongest("filler",Arrays.asList(listOne),Arrays.asList(listTwo))){
            assertTrue(i<4);
            if (i<2){
                assertEquals(listOne[i],list.get(0));
                assertEquals(listTwo[i],list.get(1));
            }
            else {
                assertEquals(listOne[i],list.get(0));
                assertEquals("filler",list.get(1));
            }
            i++;
        }

````
### 11. `Itertools.product(List<T> ... lists)`
Cartesian product of input iterables.

Roughly equivalent to nested for-loops in a generator expression. The nested loops cycle like an odometer with the rightmost element advancing on every iteration. This pattern creates a lexicographic ordering so that if the input’s iterables are sorted, the product tuples are emitted in sorted order.

````java
    public void exampleProduct(){
        String[] str1 = {"alpha","Beta","gamma"};
        String[] str2 = {"a","b"};
        String[] str3 = {"Samagra","Sammy","Shanu"};
        for (List<String> products :
                Itertools.product(Arrays.asList(str1),Arrays.asList(str2),Arrays.asList(str3))) {
            for (String s :
                    products) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
````
### 12. `Itertools.combinations(List<T> list, int r)`
Return r length subsequences of elements from the input iterable.

Combinations are emitted in lexicographic sort order. So, if the input iterable is sorted, the combination tuples will be produced in sorted order.

Elements are treated as unique based on their position, not on their value. So if the input elements are unique, there will be no repeat values in each combination.

````java
public void exampleCombinations(){
        String[] str1 = {"alpha","Beta","gamma","a","b","Samagra","Sammy","Shanu"};
        int i = 0;
        for (List<String> products :
                Itertools.combinations(Arrays.asList(str1),4)) {
            for (String s :
                    products) {
                System.out.print(s+" ");
            }
            i++;
            System.out.println(i);
        }
    }
````

### 13. `Itertools.permutations(List<T> list,int r)`
Return successive r length permutations of elements in the iterable.
Permutations are emitted in lexicographic sort order. So, if the input iterable is sorted, the permutation tuples will be produced in sorted order.

Elements are treated as unique based on their position, not on their value. So if the input elements are unique, there will be no repeat values in each permutation.

````java
public void testPermutations(){
        String[] str1 = {"alpha","Beta","gamma","a","b","Samagra"};
        int i = 0;
        for (List<String> products :
                Itertools.permutations(Arrays.asList(str1),5)) {
            for (String s :
                    products) {
                System.out.print(s+" ");
            }
            i++;
            System.out.println(i);
        }
    }

````


# References
1. Wikipedia page for [Generators](https://en.wikipedia.org/wiki/Generator_(computer_programming))
2. This project is heavily inspired from [Python itertools](https://docs.python.org/2/library/itertools.html)