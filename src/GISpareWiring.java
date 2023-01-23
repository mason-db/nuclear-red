public class GISpareWiring extends Item implements ComboUsable {
    public GISpareWiring(){
        super("Spare Wiring", "A spare wiring harness used to replace broken or worn out ones.", "You pick up the spare wiring.", true);
    }
    public void useOn(Item useOn){
        if(useOn instanceof GIPanel){
            //todo
        }
        else{
            UserInterface.outputLine("Just because you're an engineer, it doesn't mean everything needs wires.");
        }
    }
}
