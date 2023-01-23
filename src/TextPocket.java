
public class TextPocket {
    //HashMap<String, Text> textHashMap = new HashMap<>();
    CaseInsensitiveHashMap<Text> textHashMap = new CaseInsensitiveHashMap<>();
    public TextPocket(){

    }
    public void addText(Text t){
        if(textHashMap.containsKey(t.name)){
            UserInterface.outputLine("You already have that!");
        }
        else{
            textHashMap.put(t.name, t);
        }
    }
    public boolean hasText(String textName){
        return textHashMap.containsKey(textName);
    }
    public Text removeText(String textName){
        return textHashMap.remove(textName);
    }
    public Text getText(String textName){
        if(!hasText(textName)){
            UserInterface.outputLine("You don't have that text!");
            return null;
        }
        else{
            return textHashMap.get(textName);
        }
    }
    @Override
    public String toString(){
        String out = "Texts: ";
        for(Text t : textHashMap.values()){
            out += t.name + ", ";
        }
        return out.substring(0, out.length() - 2);
    }

}
