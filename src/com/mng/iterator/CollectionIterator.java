package com.mng.iterator;

import java.util.Collection;
import java.util.Iterator;

public class CollectionIterator<T> implements Iterator<T>, Iterable<T> {
	private Iterator<Collection<T>> collectionIterator;
	private Iterator<T> elementIterator;

	public CollectionIterator(Iterator<Collection<T>> iterator) {
		init(iterator);
	}

	public CollectionIterator(Collection<Collection<T>> collection) {
		init(collection.iterator());
	}

	private void init(Iterator<Collection<T>> iterator) {
		collectionIterator = iterator;
		if(collectionIterator != null){
			elementIterator = collectionIterator.next().iterator();
		}
	}


	@Override
	public boolean hasNext() {
		if (elementIterator != null && elementIterator.hasNext()) {
			return true;
		} 
		if (collectionIterator != null && collectionIterator.hasNext()) {
			elementIterator = collectionIterator.next().iterator();
			return elementIterator.hasNext();
		}
		return false;
	}

	@Override
	public T next() {
		if (hasNext())
			return elementIterator.next();
		return null;
	}

	@Override
	public void remove() {
		if (hasNext())
			elementIterator.remove();
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}
}
