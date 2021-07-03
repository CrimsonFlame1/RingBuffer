package application;

import java.util.Iterator;

class RingBuffer<T> implements Iterable<T>{
	
	private T[] array;
	private int position = 0;
	private int numberOfItems = 0;
	
	private class RingIterator implements Iterator<T> {
		
		private int readPosition = 0;
		private int itemsRead = 0;
		
		public RingIterator() {
			if(numberOfItems == array.length) {
				readPosition = position;
			}
		}

		@Override
		public boolean hasNext() {
			return itemsRead < numberOfItems;
		}

		@Override
		public T next() {
			++itemsRead;
			
			if(readPosition == array.length) {
				readPosition = 0;
			}
			return array[readPosition++];
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public RingBuffer(int size) {
		array = (T[])new Object[size];
	}
	
	public void add(T obj) {
		array[position++] = obj;
		
		if(position == array.length) {
			position = 0;
		}
		
		if(++numberOfItems > array.length) {
			numberOfItems = array.length;
		}
	}
	
	public int size() {
		return numberOfItems;
	}
	
	public T get(int index) {
		return array[index]; 
	}

	@Override
	public Iterator<T> iterator() {
		return new RingIterator();
	}
	
}

public class App {

	public static void main(String[] args) {
		RingBuffer<String> rb = new RingBuffer<>(3);
		
		rb.add("saber");
		rb.add("scatha");
		rb.add("rem");
		rb.add("ram");
		rb.add("emilia");
		
		for(var n: rb) {
			System.out.println(n);
		}
	}

}
