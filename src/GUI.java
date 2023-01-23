import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI{
    Player player;
    JFrame frame;
    JPanel mainPanel;
    ArrayList<String> previousLines = new ArrayList<>();
    JLabel[] lines = new JLabel[24];
    int currentViewIndex = 0;
    public GUI(Player player){
        this.player = player;
        frame = new JFrame();
        frame.setTitle("Nuclear Red");
        frame.setPreferredSize(new Dimension(570, 350));
        frame.setSize(new Dimension(570, 350));
        mainPanel = new JPanel();
        mainPanel.setBackground(Lib.BACKGROUND_COLOR);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new GridLayout(25, 1));
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    Lib.debugPrint("Detected up key");
                    if(!(currentViewIndex + lines.length > previousLines.size())){
                        currentViewIndex++;
                        makeDisplayLines(currentViewIndex);
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    Lib.debugPrint("Detected down key");
                    if(currentViewIndex > 0){
                        currentViewIndex--;
                        makeDisplayLines(currentViewIndex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        JTextField inputLine = new JTextField(75);
        inputLine.setFont(Lib.mainFont);
        inputLine.setForeground(Lib.FOREGROUND_COLOR);
        inputLine.setBackground(Lib.BACKGROUND_COLOR);
        inputLine.setCaretColor(Lib.FOREGROUND_COLOR);
        inputLine.setBorder(BorderFactory.createLineBorder(Lib.FOREGROUND_COLOR));
        inputLine.setText("");
        inputLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputLine.getText().equals("")) {
                    if (currentViewIndex != 0) {
                        currentViewIndex = 0;
                        makeDisplayLines(currentViewIndex);
                    }
                    UserInterface.outputLine(inputLine.getText());
                    UserInterface.turn(inputLine.getText());
                    inputLine.setText("");
                }
            }
        });
        inputLine.addKeyListener(kl);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Lib.debugPrint("Window Resized");
                super.componentResized(e);
                Lib.mainFont = new Font("Courier New", Font.BOLD, (int)(frame.getWidth() / 47.5));
                inputLine.setFont(Lib.mainFont);
                for(JLabel lab : lines){
                    lab.setFont(Lib.mainFont);
                }
            }
        });
        for(int i = 0; i < lines.length; i++){
            lines[i] = new JLabel(" ", JLabel.LEFT);
            lines[i].setFont(Lib.mainFont);
            lines[i].setForeground(Lib.FOREGROUND_COLOR);
            lines[i].setHorizontalAlignment(SwingConstants.LEFT);
        }
        frame.add(mainPanel);
        for(int i = lines.length - 1; i >= 0; i--){
            mainPanel.add(lines[i], Component.LEFT_ALIGNMENT);
        }
        //mainPanel.add(inputLine, Component.LEFT_ALIGNMENT);
        mainPanel.add(inputLine, Component.LEFT_ALIGNMENT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
    /*public void outputLine(String line){
        String[] brokenUp = line.split("\n");
        for(int s = 0; s < brokenUp.length; s++) {
            for (int i = lines.length - 1; i >= 1; i--) {
                lines[i].setText(lines[i - 1].getText());
            }
            lines[0].setText(brokenUp[s]);
        }
    }*/
    public void outputLine(String line){
        String[] brokenUp = line.split("\n");
        for(int i = 0; i < brokenUp.length; i++){
            previousLines.add(0, brokenUp[i]);
        }
        //Max out at MAX_LINE_HISTORY number of lines
        while(previousLines.size() > Lib.MAX_LINE_HISTORY){
            previousLines.remove(previousLines.size() - 1);
        }
        makeDisplayLines(0);
    }
    private void makeDisplayLines(int startIndex){
        for(int i = 0; i < lines.length; i++){
            if(startIndex + i >= previousLines.size()){
                lines[i].setText(" ");
            }
            else{
                lines[i].setText(previousLines.get(startIndex + i));
            }
        }
    }

}
