package csc207b07.example.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A MultiLinkedList is a data structure consisting of Nodes, each of which 
 * represent an InTriage Patient, and is used to order the same set of data
 * by two classifications- by time of arrival (earliest to latest), and by 
 * urgency (most to least urgent).
 * @author c2phelan
 * @author g3s
 * @author c3zhanhk
 * @author g3soul
 * @author c2zhoush
 *
 */
public class MultiLinkedList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2769256687028983404L;
	
	// The first Node in this MultiLinkedList.
	protected Node head;
	
	// The last Node in this MultiLinkedList.
	protected Node tail;
	
	/**
	 * Constructs an empty MultiLinkedList with its first Node head connected
	 * to its last Node tail via both of its links.
	 */
	public MultiLinkedList() {
		this.head = new Node();
		this.tail = new Node();
		
		// Connects both links of the head to the tail.
		this.head.nextArrival = this.tail;
		this.head.nextUrgency = this.tail;
	}
	
	/**
	 * Inserts a Node newNode into this MultiLinkedList in order of earliest to 
	 * latest arrival time.
	 * @param newNode The Node to be inserted into this MultiLinkedList.
	 */
	public void insert(Node newNode){
		
		// This MultiLinkedList is empty.
		if (this.head.nextArrival == this.tail){ 
			
			// Inserts newnode between the head and the tail along both links.
			this.head.nextArrival = newNode;
			this.head.nextUrgency = newNode;
			newNode.nextArrival = this.tail;
			newNode.nextUrgency = this.tail;
		}
		
		// This MultiLinkedList is not empty.
		else{
			
			// Gets the node immediately before where newNode should be 
			// inserted along the arrival links and repoints the arrival links.
			Node arrivalPredecessor = this.getArrivalPredecessor(newNode);
			newNode.nextArrival = arrivalPredecessor.nextArrival;
			arrivalPredecessor.nextArrival = newNode;
			
			// Gets the node immediately before where newNode should be 
			// inserted along the urgency links and repoints the urgency links.
			Node urgencyPredecessor = this.getUrgencyPredecessor(newNode);
			newNode.nextUrgency = urgencyPredecessor.nextUrgency;
			urgencyPredecessor.nextUrgency = newNode;
			
		}
	}
	
	/**
	 * Returns the Node at the position of latest arrival (i.e. the Node 
	 * immediately before the tail of this MultiLinkedList).
	 * @return
	 */
	public Node getArrivalPredecessor(Node newNode){
		Node currentNode = this.head;
		
		// currentNode is not the second last node along the arrival links
		// (including tail) and currentNode's next arrival arrived before newNode.
		while ((currentNode.nextArrival.data != null) &&
				(currentNode.nextArrival.data.getArrivalTime().before(newNode.data.getArrivalTime()))){
			
			// Traverses this MultiLinkedList to the next node along arrival links.
			currentNode = currentNode.nextArrival;
			
		}
		return currentNode;
	}
	
	public Node getUrgencyPredecessor(Node newNode){
		Node currentNode = this.head;
		
		// currentNode is not the second last node along the urgency links
		// (including tail) and currentNode's next urgency is higher than 
		// newNode's urgency.
		while((currentNode.nextUrgency.data != null) && 
				(currentNode.nextUrgency.data.getUrgencyPoint() > newNode.data.getUrgencyPoint())){
			
			// Traverses this MultiLinkedList to the next node along urgency links.
			currentNode = currentNode.nextUrgency;
		}
		
		// currentNode is the second last node along the urgency links
		// (including tail).
		if (currentNode.nextUrgency.data == null){
			return currentNode;
			
		// currentNode's next urgency is less than newNode's urgency.	
		}else if (currentNode.nextUrgency.data.getUrgencyPoint() < newNode.data.getUrgencyPoint()){
			return currentNode;			
		}
		
		// currentNode is the second last node along the urgency links
		// (including tail) and currentNode's next urgency is of equal urgency 
		// to newNode's urgency and currentNode's next urgency arrived before
		// newNode.
		else{
			while ((currentNode.nextUrgency.data != null) && (currentNode.nextUrgency.data.getUrgencyPoint() == newNode.data.getUrgencyPoint())
						&&(currentNode.nextUrgency.data.getArrivalTime().before(newNode.data.getArrivalTime()))){
					currentNode = currentNode.nextUrgency;
				
			}
		}
		return currentNode;
	}
	
	/**
	 * Returns the InTriage Patient with health card number healthCardNum.
	 * @param healthCardNum The health card number of the InTriage Patient
	 * to be retrieved.
	 * @return
	 */
	public InTriage getPatientByCardNum(String healthCardNum){

		// Begins at the second node along arrival links (including head).
		Node currentNode=this.head.nextArrival;
		
		// currentNode is not head or tail.
		while (currentNode.data != null){
			
			// currentNode is not the matching node.
			if (!currentNode.data.getHealthCardNum().equals(healthCardNum)) {
				
				// Traverses this MultiLinkedList to the next node along arrival links.
				currentNode = currentNode.nextArrival;
	}
			else {
				return currentNode.data;
			}
		}
		return currentNode.data;
        }
	
	/**
	 * Returns a List of the InTriage Patients contained within the Nodes of 
	 * this MultiLinkedList by order of earliest to latest time of arrival.
	 * @return A List of the InTriage Patients contained within the Nodes of 
	 * this MultiLinkedList by order of earliest to latest time of arrival.
	 */
	public List<InTriage> getPatientsByArrival(){
		List<InTriage> inTriageList = new ArrayList<InTriage>();
		
		// Begins at the second node along arrival links (including head).
		Node currentNode = this.head.nextArrival;
		
		// currentNode is not the head or the tail.
		while (currentNode.data != null){
			inTriageList.add(currentNode.data);
			
			// Traverses this MultiLinkedList to the next node along arrival links.
			currentNode = currentNode.nextArrival;
		}
		return inTriageList;
	}
	
	/**
	 * Returns a List of the InTriage Patients in this MultiLinkedList ordered 
	 * by most to least urgent.
	 * @return A list of the InTriage Patients in this MultiLinkedList ordered 
	 * by most to least urgent.
	 */
	public List<InTriage> getPatientsByUrgency(){
		List<InTriage> inTriageList = new ArrayList<InTriage>();
		
		// Begins at the second node along urgency links (including head).
		Node currentNode = this.head.nextUrgency;
		
		// currentNode is not the head or the tail.
		while (currentNode.data != null){
			inTriageList.add(currentNode.data);
			
			// Traverses this MultiLinkedList to the next node along urgency links.
			currentNode = currentNode.nextUrgency;
		}
		return inTriageList;
	}
	
	/**
	 * Returns whether the InTriage Patient with health card number 
	 * healthCardNum is contained within the Nodes of this MultiLinkedList.
	 * @param healthCardNum The health card number of the InTriage Patient 
	 * that is being searched for.
	 * @return
	 */
	public boolean contains(String healthCardNum){
		
		// getPatientByCardNum failed to find the matching InTriage Patient
		// in this MultiLinkedList.
		if (getPatientByCardNum(healthCardNum) == null){
			return false;
		}
		
		// This MultiLinkedList contains the matching InTriage Patient.
		else{
			return true;
		}
	}
	
	/**
	 * Removes the node in this MultiLinkedList that represents the InTriage
	 * Patient with health card number healthCardNum, if it exists.
	 * @param healthCardNum The health card number that belongs to the 
	 * InTriage Patient to be removed from this MultiLinkedList.
	 */
	public void remove(String healthCardNum){
		
		// The node to be removed if it exists.
		Node removeNode = this.getNodeByCardNum(healthCardNum);
		
		// The node immediately before removeNode along the arrival links.
		Node arrivalPredecessor = this.getArrivalPredecessor(removeNode);
		
		// The node immediately before removeNode along the urgency links.
		Node UrgencyPredecessor = this.getUrgencyPredecessor(removeNode);
		
		// Repoints nodes to exclude removeNode.
		arrivalPredecessor.nextArrival = removeNode.nextArrival;
		UrgencyPredecessor.nextUrgency = removeNode.nextUrgency;	
	}
	
	/**
	 * Returns the node containing the InTriage Patient with health card number
	 * healthCardNum, if it exists.
	 * @param healthCardNum
	 * @return
	 */
	public Node getNodeByCardNum(String healthCardNum){

		// Begins at the second Node of this MultiLinkedList along arrival 
		// links (including head).
		Node currentNode = this.head.nextArrival;
		
		// currentNode is not the head or the tail.
		while (currentNode.data != null){
			
			// currentNode is not the matching Node.
			if (!currentNode.data.getHealthCardNum().equals(healthCardNum)) {
				
				// Traverses this MultiLinkedList to the next node along arrival links.
				currentNode = currentNode.nextArrival;
	}
			// currentNode is the matching Node.
			else {
				return currentNode;
			}
		}
		return currentNode;
        }
}