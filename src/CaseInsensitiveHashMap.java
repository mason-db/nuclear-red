//https://stackoverflow.com/questions/8236945/case-insensitive-string-as-hashmap-key

import java.util.HashMap;
public class CaseInsensitiveHashMap<E> extends HashMap<String, E> {
   @Override
   public E put(String key, E value){
       return super.put(key.toLowerCase(), value);
   }
   public boolean containsKey(String key){
       return super.containsKey(key.toLowerCase());
   }
   public E get(String key){
       return super.get(key.toLowerCase());
   }
   public E remove(String key){
       return super.remove(key.toLowerCase());
   }
}
