import java.awt.*;

public class Lib {
    public static final boolean DEBUG_MODE = true;
    public static final int MAX_LINE_HISTORY = 1000;
    //https://www.theguardian.com/news/datablog/2011/mar/15/radiation-exposure-levels-guide
    public static final int MAX_EXPOSURE = 10000;
    public static final Color FOREGROUND_COLOR = new Color(255, 194, 0);
    public static final Color BACKGROUND_COLOR = new Color(30, 21, 25);
    public static Font mainFont = new Font("Courier New", Font.BOLD, 12);

    //private static HashMap<String, Room> roomMasterList = new HashMap<>();
    private static CaseInsensitiveHashMap<Room> roomMasterList = new CaseInsensitiveHashMap<>();
    public static void debugPrint(String str){
        if(DEBUG_MODE){
            //UserInterface.outputLine("DEBUG:" + str);
            System.out.println("DEBUG:" + str);
        }
    }
    public static void initalizeRooms(){
        //CryoPod
        ItemPocket cryoPodItems = new ItemPocket();
        cryoPodItems.addItem(new GICryoChamberButton());
        Room cryoPod = new Room("Cryopod", cryoPodItems, new TextPocket(), "You slip awake but can barely " +
                "tell as much. You’re surrounded by dark so deep you can barely tell that your eyes are open. You try and " +
                "raise your hands but they hit cold, hard wall on all sides of you; You’re in a freeezing, metal coffin. " +
                "The only thing you can make out with your hands is a bump directly in front of you. A button?\n" +
                "You hear the vague sound of a woman’s voice. Must be an intercom\n" +
                "\n" +
                "“Remem- Re- Re-… The BUTTON- Remember to USE the-…”\n" +
                "\n" +
                "The glitched intercom loops on and on.", 0, false);
        roomMasterList.put(cryoPod.getName(), cryoPod);

        //Prep Room
        ItemPocket introRoomItems = new ItemPocket();
        TextPocket introRoomTexts = new TextPocket();
        introRoomItems.addItem(new GIBriccBroken());
        introRoomItems.addItem(new GIOsChip());
        introRoomItems.addItem(new GIJumpsuit());
        Text JumpSuitStickyNote = new Text("Sticky Note", "A sticky note found on your jumpsuit.", "Remember, when you " +
                "USE your jumpsuit, the zipper is on the back", "You remove the sticky note from the jumpsuit and stuff it in your pocket.");
        introRoomTexts.addText(JumpSuitStickyNote);
        String introRoomIntroText = "Examining your surroundings, you see that you’re in a small prep room. a JUMPSUIT hangs " +
                "on the far side of the room beside a small metal table protruding from the wall. On the table, your personal " +
                "information device, your BRICC  (off) and it’s OS CHIP. Near these items, a DOOR\n" +
                "\n" +
                "It all comes back to you. You’re a mechanic on the Martian Continental Polar Outpost, or MarCoPolO, and your entire crew its in " +
                "Cryo-sleep awaiting supplies. You’re only to be woken up when the supplies have arrived or in the event " +
                "of a mechanical emergency. Judging from the lack of other crew around you, the latter seems more likely. It may be smart to TAKE your equipment and get to work.";
        Room prepRoom = new Room("Prep room", introRoomItems, introRoomTexts, introRoomIntroText, 0, true);
        roomMasterList.put(prepRoom.getName(), prepRoom);

        //Hub Room
        String hubIntroText = "You walk through the door of the prep room. You enter the HUB. In front of you are four " +
                "doors, labeled \"GENERATOR\", \"CONTROL\", \"REACTOR\", and \"STORAGE.\" Above the GENERATOR ROOM door, an emergency light flashes.\n" +
                "COngratulations! You have reached the current end of Nuclear Red. Stay tuned for further releases!";
        Room hub = new Room("Hub", null, null, hubIntroText, 5, true);
        roomMasterList.put(hub.getName(), hub);



        //link rooms
        cryoPod.linkedRooms.add(prepRoom);
        prepRoom.linkedRooms.add(cryoPod);
        prepRoom.linkedRooms.add(hub);
        hub.linkedRooms.add(prepRoom);

    }
    public static Room getRoom(String roomName){
        return roomMasterList.get(roomName);
    }
}
