/**
 * MainanStack.java (OOP)
 * Pemateri : Mas Arbi
 * Tanggal : 29 Nov 2013
 * Author : Harun R
 *
*/

public class MainanStack {
	public static void main(String[] args) {
		nyobaStack stack1 = new nyobaStack();
		stack1.push(11);
		stack1.push(12);
		stack1.push(13);
		stack1.push(14);
		stack1.push(14);
		stack1.push(14);
		stack1.push(14);
		stack1.push(14);
		stack1.push(14);
		stack1.print();

		stack1.pop();
		stack1.pop();
		stack1.print();

		stack1.makeEmpty();
		stack1.print();

		stack1.push(11);
		stack1.print();

	}
}

class nyobaStack {
	// Ga bisa di panggil di class lain, klo public bisa
	private Object[] theArray;
	private int topOfStack;
	private static final int DEFAULT_CAPACITY = 10;

	public nyobaStack() {
		theArray = new Object[DEFAULT_CAPACITY];
		topOfStack = -1;
	}

	public boolean isEmpty() {
		return topOfStack == -1;
	}

	public void makeEmpty() { // Gak perlu pake return
		topOfStack = -1;
	}

	public Object top() { // Melihat objek paling atas
		if(isEmpty()) System.out.println("nyobaStack top"); // Kalo kosong print ini
		return theArray[topOfStack];
	}

	public void pop() { // Menghapus objek terakhir
		if(isEmpty()) System.out.println("nyobaStack pop"); // Kalo kosong print ini
		else topOfStack--;
	}

	public Object topAndPop() {
		if(isEmpty()) System.out.println("nyobaStack topAndPop");
		return theArray[topOfStack--];
	}

	public void push(Object x) {
		if(topOfStack +1 == theArray.length) doubleArray();
		theArray[++topOfStack] = x;
	}

	public void print() {
		for (int i=0; i<=topOfStack; i++) System.out.println("Array ke " +i+ " : " +theArray[i]);
		System.out.println();
	}

	public void doubleArray() {
		Object[]tmpArray = new Object[DEFAULT_CAPACITY];
			tmpArray = theArray;
			theArray = new Object[DEFAULT_CAPACITY*2];
			theArray = tmpArray;
	}
}
