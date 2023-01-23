public class Item extends Thing {
    protected String flavorText, pickupText;
    protected boolean pickupable;
    protected static Player player;
    public Item(String name, String flavorText, String pickupText, boolean pickupable){
        super(name);
        this.flavorText = flavorText;
        this.pickupText = pickupText;
        this.pickupable = pickupable;
    }
    public String getFlavorText(){
        return flavorText;
    }
    public String getPickupText(){
        return  pickupText;
    }
    public boolean isPickupable(){
        return pickupable;
    }
    public static void setPlayer(Player player){
        Item.player = player;
    }
}
