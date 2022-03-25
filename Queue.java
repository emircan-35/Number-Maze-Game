
public class Queue {
	private Object[] elements;
	private int front;
	private int rear;
	
	Queue(int capacity){
		this.elements=new Object[capacity];
		front=0;
		rear=-1;
	}
	
	public boolean isFull() {
		return (rear+1==elements.length);
	}
	
	public boolean isEmpty() {
		return (front>rear);
	}
	
	public void enqueue(Object data) {
		if (isFull()) {
			System.out.println("Queue overflow");
		}else {
			rear++;
			elements[rear]=data;
		}
	}
	
	public Object dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		}else {
			Object data=elements[front];
			elements[front]=null;
			front++;
			return data;
		}
	}
	
	public Object peek() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		}else {
			return elements[front];
		}
	}
	
	public int size() {
		return (rear-front+1);
	}
}
