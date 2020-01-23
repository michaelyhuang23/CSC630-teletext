/**
 * Implements the doubly-linked list of messages for Teletext
 */

import java.awt.Graphics;

public class TeletextList
{
  private ListNode2 heading, topNode;

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order.  Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines)
  {
    ListNode2 blank = new ListNode2("", null, null);
    heading = new ListNode2("Today's Headlines:", blank, blank);
    blank.setPrevious(heading);
    blank.setNext(heading);
    topNode = heading;
  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg)
  {
	  addAfter(heading.getNext(),msg);
  }

  /**
   * Deletes the node that follows topNode f rom the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete()
  {
    ListNode2 node = topNode.getNext();
    if (node == heading || node == heading.getNext() || node == heading.getPrevious()) {
      return;
    }
    remove(node);
  }

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp()
  {
    topNode = topNode.getNext();
  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a referenece to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg)
  {
    ListNode2 newNode = new ListNode2(msg, node.getPrevious(), node);
    node.getPrevious().setNext(newNode);
    node.setPrevious(newNode);
    return newNode;
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a referenece to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg)
  {
    ListNode2 newNode= new ListNode2(msg, node,node.getNext());
    node.getNext().setPrevious(newNode);
    node.setNext(newNode);
    return newNode;
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node)
  {
     node.getPrevious().setNext(node.getNext());
     node.getNext().setPrevious(node.getPrevious());
     node.setNext(null);
     node.setPrevious(null);
  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines)
  {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++)
    {
      g.drawString((String)node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
}
