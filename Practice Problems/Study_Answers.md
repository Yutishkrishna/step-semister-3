# Week 9 — Quiz and Concept Answers

## Part B: Quiz
1. **B — O(1)**: Direct array-index access.
2. **C — Insertion**: Adding a new element.
3. **C**: Match operations and constraints to the data structure.
4. **C — Time vs space trade-offs**: A cache uses memory to save computation.
5. **B — O(m + n)**: Separate consecutive loops add their costs.
6. **C — Linear data structure**: Elements form a sequence.
7. **C — O(n)**: Ignore constant factors and additive constants.
8. **D — Linked list**: A non-primitive data structure.
9. **B — O(m × n)**: Visit every cell.
10. **C**: Organize, store, and access data efficiently.

## Part C: Concept Questions

### 1. Sorted versus unsorted array search
Searching an unsorted array generally requires checking elements sequentially: O(n) worst-case time. A sorted array allows binary search in O(log n) time, since each comparison discards half the remaining elements.

### 2. Why 2n is O(n)
Big-O describes the rate of growth as input size increases, ignoring constant multipliers. The expressions n and 2n both grow linearly, so O(2n) simplifies to O(n).

### 3. Linear versus non-linear traversal
In a linear structure such as an array, elements are visited in sequence. In a non-linear structure such as a tree, elements have branching relationships, so traversal uses a strategy such as depth-first or breadth-first search.
