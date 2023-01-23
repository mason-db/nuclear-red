public class Text extends Thing {
    protected String description, text, pickupText;
    public Text(String name, String description, String text, String pickupText){
        super(name);
        this.description = description;
        this.text = text;
        this.pickupText = pickupText;
    }
    public String getDescription(){
        return description;
    }
    public String getText(){
        return text;
    }
    public String getPickupText(){
        return pickupText;
    }
}
