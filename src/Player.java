public class Player {
    private TextPocket textPocket = new TextPocket();
    private ItemPocket itemPocket = new ItemPocket();
    private int exposure = 0;
    private Room currentRoom = null;

    public int getExposure(){
        return exposure;
    }
    public int increaseExposure(int amount){
        exposure += amount;
        if(exposure < 0){
            exposure = 0;
        }
        return exposure;
    }
    public int setExposure(int amount){
        exposure = amount;
        if(exposure < 0){
            exposure = 0;
        }
        return exposure;
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setRoom(Room r){
        currentRoom = r;
    }
    public TextPocket getTexts(){
        return textPocket;
    }
    public ItemPocket getItems(){
        return itemPocket;
    }
}
