/*
 * Problem: Implement a Stack using an Array
 *
 * Build a stack (LIFO - last in first out) from scratch with a plain array.
 * Support push, pop, peek, isEmpty, size.
 *
 * Approach: keep an array and a "top" index that points at the last pushed
 *           element (-1 means empty). push = bump top and store; pop = read then
 *           drop top. nothing fancy, just gotta handle overflow (full) and
 *           underflow (empty) so we don't go out of bounds.
 *
 * Time: O(1) for all ops
 * Space: O(n)
 *
 * Topics: stack, array, data structure design
 */
public class ImplementStackUsingArray {

    int[] data;
    int top;

    ImplementStackUsingArray(int capacity) {
        data = new int[capacity];
        top = -1;          // empty
    }

    void push(int x) {
        if (top == data.length - 1) {
            System.out.println("stack full, can't push " + x);
            return;
        }
        data[++top] = x;
    }

    int pop() {
        if (isEmpty()) throw new RuntimeException("stack empty");
        return data[top--];
    }

    int peek() {
        if (isEmpty()) throw new RuntimeException("stack empty");
        return data[top];
    }

    boolean isEmpty() { return top == -1; }
    int size() { return top + 1; }

    public static void main(String[] args) {
        ImplementStackUsingArray st = new ImplementStackUsingArray(3);
        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println("peek: " + st.peek());   // expected: 30
        System.out.println("pop: " + st.pop());      // expected: 30
        System.out.println("pop: " + st.pop());      // expected: 20
        System.out.println("size: " + st.size());    // expected: 1
        System.out.println("isEmpty: " + st.isEmpty()); // expected: false
    }
}
