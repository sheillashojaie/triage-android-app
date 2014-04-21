package csc207b07.example.data;

import java.io.Serializable;

/**
 * A Node is an element which makes up a MultiLinkedList and represents a
 * single InTriage Patient. Each Node has two pointers- one to the next Node
 * containing the InTriage Patient of the next least urgent compared to the
 * InTriage Patient contained in this Node, and one to the Node containing
 * the InTriage Patient who arrived after the InTriage Patient contained in
 * this Node.
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 */
public class Node implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 251041322148190909L;

	/** The InTriage Patient to be carried within this Node.*/
	protected InTriage data;
	
	/** The Node which contains the InTriage Patient of the next highest 
	 * urgency compared to the InTriage Patient contained in this Node.*/
	protected Node nextUrgency; 
	
	/** The Node which contains the InTriage Patient who arrived after the 
	 * InTriage Patient contained in this Node.*/
	protected Node nextArrival;
	
	/**
	 * Constructs a Node and sets its data, nextUrgency, and nextArrival to 
	 * null upon initialization.
	 */
	public Node(){
		this.data = null;
		this.nextUrgency = null;
		this.nextArrival = null;
	}
	
	/**
	 * Sets this Node's data to InTriage Patient data.
	 * @param data
	 */
	public void addData(InTriage data){
		this.data = data;
        }
}
