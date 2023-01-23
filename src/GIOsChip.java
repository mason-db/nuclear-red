public class GIOsChip extends Item implements ComboUsable {
    public GIOsChip(){
        super("OS Chip", "A BRICC compatible memory chip containing BRICC OS.", "You pick up the OS Chip.", true);
    }
    public void useOn(Item useOn){
        if(useOn instanceof GIBriccBroken){
            player.getItems().removeItem("BRICC");
            player.getItems().removeItem("OS Chip");
            player.getItems().addItem(new GIBricc());
            Flags.setFlag("Fixed BRICC", true);
            UserInterface.outputLine("You insert the OS chip into the nonfunctional BRICC. You now have your BRICC! " +
                    "Essentially the mechanics tool to keep track of tools. If you forget what you’ve collected or need to READ a text snippet, the BRICC is sure to HELP.");
            if(Flags.getFlag("Put on suit")){
                UserInterface.outputLine("Your activated BRICC seems to trigger another intercom.");
                Flags.setFlag("Finished intro room", true);
                UserInterface.outputLine("\"Nuclear generator malfunctioning. Mechanical attention required.\"\n\nNow's the time to ENTER the HUB room and get to fixing.");
                player.getCurrentRoom().unlockDoor("Hub");
            }
        }
        else{
            UserInterface.outputLine("As hard as you try, there's no way the OS Chip is going to fit in that.");
        }
    }
}
