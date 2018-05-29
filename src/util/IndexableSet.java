package util;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class IndexableSet <T> extends LinkedHashSet<T>{

	public static <T> T get(Set <T> set, int index) {
		if (index < set.size()) {
			int i = 0;
			for (T elt : set) {
				if (i == index)
					return elt;
				i++;
			}
			return null;
			//throw new Exception("El indice del IndexableSet esta fuera de sus limites");
		} else {
			throw new IndexOutOfBoundsException("El indice del IndexableSet esta fuera de sus limites");
		}
	}
	
	public static <T> T get(Set <T> set, T object) {
		for (T elt : set) {
			if (elt.equals(object))
				return elt;
		}
		return null;
	}
	
	public T get (int index) {
		
		return get(this, index);

	}

	public T get (T object) {
		
		return get(this, object);
	}
	
}
