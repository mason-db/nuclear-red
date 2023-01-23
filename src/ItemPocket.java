import java.util.HashMap;

public class ItemPocket {
    //HashMap<String, Item> itemHashMap = new HashMap<>();
    CaseInsensitiveHashMap<Item> itemHashMap = new CaseInsensitiveHashMap<>();
    public ItemPocket(){

    }
    public void addItem(Item i){
        if(itemHashMap.containsKey(i.name)){
            UserInterface.outputLine("You already have that!");
        }
        else{
            itemHashMap.put(i.name, i);
        }
    }
    public boolean hasItem(String itemName){
        return itemHashMap.containsKey(itemName);
    }
    public Item removeItem(String itemName){
        return itemHashMap.remove(itemName);
    }
    public Item getItem(String itemName){
        if(!hasItem(itemName)){
            UserInterface.outputLine("You don't have that item!");
            return null;
        }
        else{
            return itemHashMap.get(itemName);
        }
    }
    @Override
    public String toString(){
        String out = "Items: ";
        for(Item i: itemHashMap.values()){
            out += i.name + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

}
