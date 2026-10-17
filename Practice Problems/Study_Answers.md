# Week 10 Practice — Quiz and Concept Answers

## Part B: Quiz answer key
1. **B** — Protected `age` and public `getName()` are accessible to Student; private `name` is not directly accessible.
2. **C** — Diagram 3 attempts multiple class inheritance, which Java does not support.
3. **B** — Constructors execute from base to derived: Person, Employee, Manager.
4. **C** — A subclass can directly access a protected member; it cannot access private fields or override final methods.
5. **B** — Runtime polymorphism invokes Bus's overridden `fare()`.
6. **B** — Different parameter lists are overloading; redefining the same signature in a subclass is overriding.
7. **A** — A Shape reference directly exposes members declared in Shape, not Circle-specific members.
8. **C** — Dynamic dispatch calls Circle.area() and Square.area() for their respective objects.
9. **B** — Casting Current to Savings fails at runtime; casting it to Current succeeds.
10. **B** — Polymorphism permits common processing through a Plan reference.
11. **A** — `printDocument()` is the public service; buffer and helper operations are implementation details.
12. **B** — A Vehicle reference can refer to a Car instance; an abstract Vehicle cannot be instantiated directly.
13. **B** — A non-abstract subclass must implement inherited abstract methods or itself be abstract.
14. **B** — A concrete MonthlyReport must implement `generateBody()`.
15. **A** — An interface specifies a contract; it does not normally represent per-object instance state.
16. **B** — Interface abstract methods are implicitly public and abstract.
17. **C** — DataProcessor must implement both serialize() and reset().
18. **C** — An interface suits a shared protocol for unrelated classes.
19. **B** — Dog IS-A Animal; Eagle CAN-DO Flyable.
20. **A** — equals(), hashCode(), and toString() are common root-object methods that classes often override.

## Part C: Concept answers

### 1. Overloading versus overriding
Overloading uses the same method name with different parameter lists and is resolved at compile time. Overriding provides a subclass implementation with the same signature and is selected at runtime according to the actual object.

### 2. Constructor invocation
A superclass constructor runs before the subclass constructor. In Java, `super(arguments)` passes values to the superclass constructor and must be the first statement in a constructor when explicitly used.

### 3. Concrete class, abstract class, and interface
A concrete class can be instantiated and can hold state and implemented behavior. An abstract class cannot be instantiated directly and can combine fields, constructors, and implemented or abstract methods. An interface defines a contract that unrelated classes can implement; use it when a common capability is needed without a shared class hierarchy.

### 4. Same method in two interfaces
If two interfaces declare the same abstract method signature, one public implementation in the class can satisfy both contracts. This demonstrates that a class can implement multiple interfaces.

### 5. equals() and hashCode()
If two objects are equal according to equals(), they must return the same hash code. Overriding equals() without hashCode() can cause logically equal objects to behave as separate entries in HashSet or as unexpected keys in HashMap.

### 6. Shallow copy versus deep copy
A shallow copy duplicates the outer object but shares references to nested mutable objects. Changing a shared nested list can therefore affect both copies. A deep copy duplicates the nested mutable objects as well, keeping the copies independent.

### 7. Inner class versus static nested class
A non-static inner class is associated with an instance of the enclosing class and can access its instance members. A static nested class does not need an enclosing instance and cannot directly access enclosing instance members. Use an inner class when it needs the outer object's state; use a static nested class for a helper type that does not.

### 8. Array operation complexity
Reading an element by index is O(1) because the location is calculated directly. Inserting or deleting in the middle of a fixed-size array is O(n) because later elements may need to shift.
