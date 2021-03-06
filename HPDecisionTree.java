/*
 * FILE NAME: HPDecisionTree.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Implements the EventTree interface to create an array representation of a tree. 
 * NOTE: Joyce created this class.
 * All parts of the class are functioning properly.
 */

import java.util.Iterator;
import javafoundations.*;

public class HPDecisionTree<T extends Comparable> implements EventTree<T>, Iterable<T> {
  //INSTANCE VARIABLES
  private T[] tree;
  private T root;
  private int count; 
  
  /*
   * 1-argument constructor that sets the root of a new tree. 
   * Initializes the root, array that will hold the members of the tree, 
   * and the count variable that keeps track of the number of members in the family. 
   * @param root Root of the new tree
   */
  public HPDecisionTree (T root) {
    this.tree = (T[]) (new Comparable[1]);
    this.root = root;
    this.tree[0] = this.root;
    this.count = 1;
    this.expandCapacity(root);
  }
  
  /*
   * Private helper method to expand the capacity of tree 
   * to include ancestors. Tree is expanded to include spots for the paternal and maternal ancestors of argument target,
   * intended to be the greatest index of the non-null objects in tree array. 
   * @param target oldest, rightmost ancestor in genealogy tree.
   */
  private void expandCapacity(T target) {
    int size = tree.length;
    int index = this.indexOf(target);
    
    T[] larger = (T[]) (new Comparable[(index*2)+3]); 
    for (int i = 0; i < size; i++)
      larger[i] = tree[i];
    
    tree = larger;
  }

  /* 
   * Returns the element stored in the root (aka CoTU) of the tree.
   * @return the root of the tree
   */
  public T getRoot() {
    return root; 
  }
  
  /*
   * Private helper method to return the index of the desired object in the array.
   * @param target desired object to get index of 
   * @return index of the target, -1 if in array
   */
  private int indexOf(T target) {
    int index = java.util.Arrays.asList(tree).indexOf(target);
    return index > -1 ? index : -1;
  }
  
  /*
   * Returns the member that is the paternal ancestor of target.
   * @param target desired object to get paternal ancestor of
   * @return paternal ancestor of target, null if left ancestor does not exist.
   */
 public T getLeft(T target) {
    return tree[(this.indexOf(target)*2)+1]; 
  }
  
  /*
   * Sets the left child of the tree target to lchild. Throws an exception if target is not already in the tree.
   * If the desired paternal ancestor already exists elsewhere on the tree, prints an error message. 
   * @param target member to set paternal ancestor of
   * @param lchild paternal ancestor.
   */
  public void setLeft(T target, T lchild) {
    int temp = (this.indexOf(target)*2)+1; // where the child should be placed
    
    if (temp >= tree.length) // if this position is not in the array, throw an exception
      throw new IllegalArgumentException("Target is not in the tree, cannot set left child");
    
    else if (!this.appears(lchild)) { // set the paternal as lchild
      tree[temp] = lchild;
      this.expandCapacity(lchild); // increase capacity because you added someone
      count++;
    }
    else
      System.out.println("Left child, " + lchild + ", already exists in the tree."); 
  }
  
  /*
   * Returns the member that is the maternal ancestor of target.
   * @param target desired object to get maternal ancestor of
   * @return left ancestor of target, null if left ancestor does not exist.
   */
  public T getRight(T target) {
    return tree[(this.indexOf(target)*2)+2];  //  Maternal ancestors are on the right
  }
  
  /*
   * Sets the right child of the tree target to rchild. Throws an exception if target is not already in the tree.
   * If the desired maternal ancestor already exists elsewhere on the tree, prints an error message. 
   * @param target member to set maternal ancestor of
   * @param rchild maternal ancestor.
   */
  public void setRight(T target, T rchild) {
    int temp = (this.indexOf(target)*2)+2;
    
    if (temp >= tree.length) 
      throw new IllegalArgumentException("Target is not in the tree, cannot set right child.");
    
    else if (!this.appears(rchild)) {
      tree[temp] = rchild;
      this.expandCapacity(rchild); // increase capacity because you added someone
      count++;
    }
    else
      System.out.println("Right child, " + rchild + ", already exists in the tree."); 
  }
  
  /*
   * Returns true if the tree contains an element that matches the specified target element and false otherwise. 
   * @param target member to see if already in the tree
   * @return boolean indicating if target is in tree
   */ 
  public boolean appears (T target) {
    for (T member : tree) {
      if (member != null && target.compareTo(member) == 0)
        return true;
    }
    return false;
  }

  /* 
   * Returns the number of members in this ancestral tree.
   * @return number of members in tree
   */
  public int size() {
    return count;
  }
  
  /*
   * Returns the string representation of the binary tree, one line per level.
   * @return string representation
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    Iterator<T> temp = this.byGenerations();
    
    double i = 1.0;
    while (temp.hasNext()) {
      T current = temp.next();
      
      // prints all the members in the same generation first on one line
      if (this.indexOf(current) < (Math.pow(2.0, i)-1)) 
        s.append(current.toString() + " ");
      else{
        i += 1.0;
        s.append("\n" + current.toString() + " " );
      }
    }
    return s.toString();
  }

  
  /*
   * Populates and returns an iterator that contains a level-order traversal on the ancestral tree.
   * @return iterator containing members by generation
   */
  public Iterator<T> byGenerations() {
    LinkedQueue<T> queue = new LinkedQueue<T>();
    ArrayIterator<T> iter = new ArrayIterator<T>();
    
    if (root != null) {
      queue.enqueue(root);
      while (!queue.isEmpty()) {
        T current = queue.dequeue();
        
        iter.add (current);
        
        if (this.getLeft(current) != null)
          queue.enqueue(this.getLeft(current));
        if (this.getRight(current) != null)
          queue.enqueue(this.getRight(current));
      }
    }
    return iter;
  }

  /*
   * Satisfies the Iterable interface using a level-order traversal
   * @return iterator containing members in level-order
   */
  public Iterator<T> iterator() {
    return byGenerations();
  }
  
  public T[] getArray() {
    return tree;
  }
  
  public static void main(String[] args) {
    HPDecisionTree<Integer> test = new HPDecisionTree<Integer>(37);
    System.out.println("tree with one root (" + test.getRoot() + "):\n" + test);
    test.setLeft(37, 24);
    test.setRight(37, 62);
    System.out.println("tree with two levels:\n" + test);
    test.setLeft(24, 19);
    test.setRight(24, 29);
    test.setLeft(62, 47);
    test.setRight(62, 79);
    System.out.println("tree with three levels:\n" + test);
    System.out.println("\nthe right child of 62 (79): " + test.getRight(62));
    System.out.println("the left child of 24 (19): " + test.getLeft(24));
    System.out.println("try to add 29 again as left child (error message): ");
    test.setLeft(79, 29);
    System.out.println("try to add 62 again as left child (error message): ");
    test.setRight(19, 62);
    System.out.println("does 47 appear in the tree (true)? " + test.appears(47));
    System.out.println("size of the tree (7): " + test.size());                 
  
  }
  
}
