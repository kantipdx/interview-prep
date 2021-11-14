package ds.dstype.linkedListType;

public class FlattenMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        flattentail(head);
        return head;
    }

    // flattentail: flatten the node "head" and return the tail in its child (if exists)
    // the tail means the last node after flattening "head"

    // Five situations:
    // 1. null - no need to flatten, just return it
    // 2. no child, no next - no need to flatten, it is the last element, just return it
    // 3. no child, next - no need to flatten, go next
    // 4. child, no next - flatten the child and done
    // 5. child, next - flatten the child, connect it with the next, go next

    private Node flattentail(Node head) {
        if (head == null) return head; // CASE 1
        if (head.child == null) {
            if (head.next == null) return head; // CASE 2
            return flattentail(head.next); // CASE 3
        }
        else {
            Node child = head.child;
            head.child = null;
            Node next = head.next;
            Node childtail = flattentail(child);
            head.next = child;
            child.prev = head;
            if (next != null) { // CASE 5
                childtail.next = next;
                next.prev = childtail;
                return flattentail(next);
            }
            return childtail; // CASE 4
        }
    }
}
