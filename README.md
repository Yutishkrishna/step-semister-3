# STEP Semester 3 — Week 9 Coding Assignment

Category B: Data Structures (Java)

| Question | Java file | Approach | Time | Extra space |
|---|---|---|---|---|
| 1 Class Topper Finder | A1_ClassTopperFinder.java | 2D array traversal | O(mn) | O(1) |
| 2 Merging Two Token Queues | A2_MergingTwoTokenQueues.java | Two pointers | O(m+n) | O(m+n) output |
| 3 Most Popular Canteen Order | A3_MostPopularCanteenOrder.java | Hash map | O(n) expected | O(u) |
| 4 Hot Weather Alert Windows | A4_HotWeatherAlertWindows.java | Sliding window | O(n) | O(1) |
| 5 Ticket Price Slot Finder | A5_TicketPriceSlotFinder.java | Binary search | O(log n) | O(1) |

Complexity comparisons:
- Merging and then sorting: O((m+n) log(m+n)); two-pointer merge: O(m+n).
- Repeated counting: O(n²) time, O(1) auxiliary space; hashing: O(n) expected time, O(u) space.
- Recalculating each window: O(nk); sliding window: O(n).
- Linear price scan: O(n); binary search: O(log n). Both O(1) extra space.

Compile any file using javac Filename.java, then run java ClassName.


## Additional Category B linked-list problems

| Problem | Java file | Time complexity | Extra space |
|---|---|---|---|
| Priority Token Insertion | A6_PriorityTokenInsertion.java | addFirst O(1), addLast O(n), insertAt O(n) | O(1) per operation |
| Cancelled Orders Cleanup | A7_CancelledOrdersCleanup.java | O(n) | O(1) |
| Round-Robin Game Turns | A8_RoundRobinGameTurns.java | addLast O(1), printTurns O(turns) | O(1) auxiliary |

These three additional PDFs were added as separate Java solutions. Each program uses a custom linked-list node; the round-robin implementation uses a circular singly linked list with a tail reference.
