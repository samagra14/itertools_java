## Combinatorial_lib for java
The aim of this library is to provide an interface
 similar to `itertools` in python.

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