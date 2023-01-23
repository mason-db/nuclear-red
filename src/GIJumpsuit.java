public class GIJumpsuit extends Item implements SoloUsable {
    public GIJumpsuit(){
        super("Jumpsuit", "A Mars-rated protection suit. You can't seem to get it open enough to slip into however.", "You GET the JUMPSUIT. On the back of the JUMPSUIT is a STICKY NOTE that you can READ.", true);
    }
    public void use(){
        if(player.getTexts().hasText("Sticky Note")){
            UserInterface.outputLine("Just as the sticky note said, you discover a hidden zipper on the back of the jumpsuit. You quickly put it on and curse your lack of zipper noticing skills.");
            player.getItems().removeItem("Jumpsuit");
            Flags.setFlag("Put on suit", true);
            if(Flags.getFlag("Fixed BRICC")){
                UserInterface.outputLine("Your activated suit seems to trigger another intercom.");
                Flags.setFlag("Finished intro room", true);
                UserInterface.outputLine("\"Nuclear generator malfunctioning. Mechanical attention required.\"\n\nNow's the time to ENTER the HUB room and get to fixing.");
                player.getCurrentRoom().unlockDoor("Hub");
            }
        }
        else{
            UserInterface.outputLine("You look all over the suit, but you can't seem to get it open. Maybe there are instructions somewhere?");
        }
    }
}
