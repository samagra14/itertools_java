## Combinatorial_lib for java [WIP]
The aim of this library is to provide an interface
 similar to `itertools` in python.This module implements
 a number of iterator building blocks inspired by
  constructs from APL, Haskell, SML and Python. Each has
   been recast in a form suitable for Java.
   
  For instance, SML provides a tabulation tool:
   tabulate(f) which produces a sequence f(0), f(1),
    .... The same effect can be achieved using this library
    by combining `Itertools.imap()` and `count()` to form `Itertools.imap(f, Itertools.count())`.
                                  
**This library uses generator like constructs to provide iterators and hence
has no memory and speed limitations as a particular element is generated only when it is needed.**

The following methods have been implemented in the library and are ready for use.

### Infinite Iterators
| Iterator        | Arguments  | Results  | Example |
| ------------- |:-------------:| -----:|  -----:|
| `count()`     | start,[step] | start, start+step , start+2*step,... | count(10) --> 10 11 12 13 14 ...|
| `cycle()`      | p      |   p0, p1, … plast, p0, p1, … |cycle('ABCD') --> A B C D A B C D ...|
| `repeat()` | elem [,n]      |    elem, elem, elem, … endlessly or up to n times | repeat(10, 3) --> 10 10 10|

### Iterators terminating on the shortest input sequence:
| Iterator        | Arguments  | Results  | Example |
| ------------- |:-------------:| -----:|  -----:|
| `chain()`     | p, q, …        |p0, p1, … plast, q0, q1, …| chain('ABC', 'DEF') --> A B C D E F|
| `compress()`  |data, selectors | (d[0] if s[0]), (d[1] if s[1]), …| compress('ABCDEF', [1,0,1,0,1,1]) --> A C E F|
| `dropwhile()` | pred, seq      |seq[n], seq[n+1], starting when pred fails | dropwhile( x-> x<5, [1,4,6,4,1]) --> 6 4 1|


### How to use ?
1. Include [jar file](https://github.com/samagra14/itertools_java/releases/download/0.01/combinatorial_lib.jar) from [releases](https://github.com/samagra14/itertools_java/releases).
2. Use them as shown below.

### Example Usages
#### Combinations
To iterate over all combinations of `k` objects from a `List<T> list` just use
`CombinationGenerator.generateCombinations(list,k)`
````java
for (List<T> possibleCombinations :
                CombinationGenerator.generateCombinations(list,k)) {
            //perform operations on your combinations
        }
````
#### Permutations
To iterate over all permutations of `k` objects from a `List<T> list` just use
`CombinationGenerator.generateCombinations(list,k)`
````java
for (List<T> possiblePermutations :
                PermutationGenerator.generatePermutations(list,k)) {
            //perform operations on your permutations
        }
````