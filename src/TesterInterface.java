public class TesterInterface extends UserInterface {
    public static void main(String[] args){
        gui = new GUI(player);
        Lib.debugPrint("PROGRAM START");
        Lib.initalizeRooms();
        Item.setPlayer(player);
        //todo
        //player.setRoom(Lib.getRoom("Cryopod"));
        player.setRoom(Lib.getRoom("Prep room"));
        outputLine(player.getCurrentRoom().getIntroText());
        player.getCurrentRoom().unlockDoor("Hub");
    }
}
