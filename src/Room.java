import java.util.ArrayList;

public class Room {
    private String name, introText;
    ItemPocket items;
    TextPocket texts;
    boolean locked;
    ArrayList<Room> linkedRooms = new ArrayList<>();
    int radiation;
    public Room(String name, ItemPocket items, TextPocket texts, String introText, int radiationPerTurn, boolean locked){
        this.name = name;
        this.items = items;
        this.texts = texts;
        this.introText = introText;
        this.radiation = radiationPerTurn;
        this.locked = locked;
    }
    public String getName(){
        return name;
    }
    public String getIntroText(){
        return introText;
    }
    public boolean addLinkedRoom(Room room){
        if(linkedRooms.contains(room)){
            return false;
        }
        else{
            linkedRooms.add(room);
            return true;
        }
    }
    public boolean exit(Player player){
        if(linkedRooms.size() == 1 && !linkedRooms.get(0).locked){
            return moveRooms(player, linkedRooms.get(0).name);
        }
        UserInterface.outputLine("Leave to where?");
        return false;
    }
    public boolean linkedTo(String roomName){
        String roomNameLowercase = roomName.toLowerCase();
        for(int i = 0; i < linkedRooms.size(); i++){
            if(linkedRooms.get(i).getName().toLowerCase().equals(roomNameLowercase)){
                return true;
            }
        }
        return false;
    }
    public boolean linkedTo(Room room){
        return linkedRooms.contains(room);
    }
    public Room getRoom(String roomName){
        String roomNameLowercase = roomName.toLowerCase();
        for(int i = 0; i < linkedRooms.size(); i++){
            if(linkedRooms.get(i).getName().toLowerCase().equals(roomNameLowercase)){
                return linkedRooms.get(i);
            }
        }
        return null;
    }
    public boolean unlockDoor(String room){
        if(getRoom(room) != null){
            getRoom(room).locked = false;
            return true;
        }
        return false;
    }
    public static boolean moveRooms(Player player, String moveTo){
        Room playersRoom = player.getCurrentRoom();
        if(!playersRoom.linkedTo(moveTo)){
            UserInterface.outputLine("That's not a room!");
            return false;
        }
        Room moveRoom = playersRoom.getRoom(moveTo);
        if(moveRoom.locked){
            UserInterface.outputLine("The door is locked.");
            return false;
        }
        else{
            player.setRoom(moveRoom);
            UserInterface.outputLine(moveRoom.introText);
            return true;
        }

    }
}
