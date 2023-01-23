public class GICryoChamberButton extends Item implements SoloUsable{
    public GICryoChamberButton(){
        super("Button", "A large, red button.", null, false);
    }
    public void use(){
        UserInterface.outputLine("The front wall of the freezing darkness splits open. Piercing light hits your blurry eyes as the enclosed chamber opens. Maybe you should LOOK around or INSPECT the room to help your eyes adjust.");
        player.getCurrentRoom().unlockDoor(player.getCurrentRoom().linkedRooms.get(0).getName());
        player.setRoom(player.getCurrentRoom().linkedRooms.get(0));
    }
}
