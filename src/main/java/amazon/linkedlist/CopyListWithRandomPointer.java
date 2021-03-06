package amazon.linkedlist;

import java.util.HashMap;

 class NodeList {
    public int val;
    public NodeList next;
    public NodeList random;

    public NodeList() {
    }

    public NodeList(int _val, NodeList _next, NodeList _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}


public class CopyListWithRandomPointer {
    HashMap<NodeList, NodeList> visitedHash = new HashMap<NodeList, NodeList>();
    
    public static void main(String args[]){
        
    }

    public NodeList copyRandomList(NodeList head) {

      if (head == null) {
        return null;
      }

      // If we have already processed the current node, then we simply return the cloned version of
      // it.
      if (this.visitedHash.containsKey(head)) {
        return this.visitedHash.get(head);
      }

      // Create a new node with the value same as old node. (i.e. copy the node)
      NodeList node = new NodeList(head.val, null, null);

      // Save this value in the hash map. This is needed since there might be
      // loops during traversal due to randomness of random pointers and this would help us avoid
      // them.
      this.visitedHash.put(head, node);

      // Recursively copy the remaining linked list starting once from the next pointer and then from
      // the random pointer.
      // Thus we have two independent recursive calls.
      // Finally we update the next and random pointers for the new node created.
      node.next = this.copyRandomList(head.next);
      node.random = this.copyRandomList(head.random);

      return node;
    }
}
