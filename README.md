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
