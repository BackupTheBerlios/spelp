package Bourse;

import java.lang.reflect.Field;
import java.util.Collection;

public class Util {
	public static Object getElement (Collection<?> c, Long id, String idField) {
		for (Object o : c){
			for (Field f : o.getClass().getDeclaredFields()){
				if (f.getName().equals(idField)){
					try {
						f.setAccessible(true);
						if (f.get(o).equals(id)){
							f.setAccessible(false);
							return o ;
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null ;	
	}
}
