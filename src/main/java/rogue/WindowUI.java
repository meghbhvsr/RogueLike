package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import java.awt.FlowLayout;
import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
//import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JList;
//import java.awt.Dimension;
//import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
import java.io.File;
//import javax.swing.ListSelectionModel;

public class WindowUI extends JFrame {


    private SwingTerminal terminal;
    private TerminalScreen screen;
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 2100;
    // Screen buffer dimensions are different than terminal dimensions
    public static final int COLS = 80;
    public static final int ROWS = 24;
    private final char startCol = 0;
    private final char msgRow = 1;
    private final char roomRow = 3;
    private static Container contentPane;
    private final int tF = 25;
    private static final int FIVE = 5;
    private static final int FIFTY = 50;
    private static final int ONEFIFTY = 150;
    private static final int SEVENTYFIVE = 150;
    private static final int WIDTH2 = 200;
    private static final int HEIGHT2 = 150;
    private static final int NINETY = 90;
    private static JList myList = new JList();
    private static String m = new String();
    private static JTextField dataEntry = new JTextField();
    private static JLabel exampleLabel;
    private static ArrayList<Item> inventory = new ArrayList<Item>();
    private static Rogue g;

/**
Constructor.
@throws ImpossiblePositionException
**/

    public WindowUI() throws ImpossiblePositionException {
        super("my awesome game");
        contentPane = getContentPane();
        setWindowDefaults(getContentPane());
        setUpPanels();
        createMenuBar();
        setTerminal();
        pack();
        start();
    }

    private void setWindowDefaults(Container contentPane2) {
        setTitle("Rogue!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane2.setLayout(new BorderLayout());

    }

    private void setTerminal() {
        JPanel terminalPanel = new JPanel();
        terminal = new SwingTerminal();
        terminalPanel.add(terminal);
        contentPane.add(terminalPanel, BorderLayout.CENTER);
    }

    private void setUpPanels() {
        JPanel labelPanel = new JPanel();
        setUpLabelPanel(labelPanel);
        setTerminal();
    }

    private void setUpLabelPanel(JPanel labelPanel) {
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        labelPanel.setBorder(prettyLine);
        exampleLabel = new JLabel();
        labelPanel.add(exampleLabel);
        contentPane.add(labelPanel, BorderLayout.SOUTH);
    }

    private void start() {
        try {
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(TerminalPosition.TOP_LEFT_CORNER);
            screen.startScreen();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMenuBar() {
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        JMenu file = new JMenu("File");
        bar.add(file);
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(hi -> open(new File("rogue.ser")));
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(hello -> save());
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(b -> exit());
        JMenuItem changePlayer = new JMenuItem("ChangePlayerName");
        changePlayer.addActionListener(w -> setPlayerName());
        file.add(open);
        file.add(save);
        file.add(exit);
        file.add(changePlayer);
    }
    private void exit() {
        System.exit(0);
    }
    private void setPlayerName() {
        String input = JOptionPane.showInputDialog(null, "Enter new player name: ");
        g.getPlayer().setName(input);
        exampleLabel.setText("player: " + g.getPlayer().getName());
    }
    private void save() {
        try {
            //String input = JOptionPane.showInputDialog(null, "Enter an item u want to eat:  ");
            FileOutputStream fileOut = new FileOutputStream("rogue.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(g);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
       }
    }

    private void open(File fName) {
        try {
            FileInputStream fileInput = new FileInputStream("rogue.ser");
            ObjectInputStream input = new ObjectInputStream(fileInput);
            g = (Rogue) input.readObject();
            input.close();
            fileInput.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        System.out.println("deserialized");
    }

    /**
    Prints a string to the screen starting at the indicated column and row.
    @param toDisplay the string to be printed
    @param column the column in which to start the display
    @param row the row in which to start the display
    **/
    public void putString(String toDisplay, int column, int row) {
        Terminal t = screen.getTerminal();
        try {
            t.setCursorPosition(column, row);
        for (char ch: toDisplay.toCharArray()) {
            t.putCharacter(ch);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
Changes the message at the top of the screen for the user.
@param msg the message to be displayed
**/
    public void setMessage(String msg) {
        putString("                                                ", 1, 1);
        putString(msg, startCol, msgRow);
    }

    /**
    Redraws the whole screen including the room and the message.
    @param message the message to be displayed at the top of the room
    @param room the room map to be drawn
    **/
    public void draw(String message, String room) {
        try {
            terminal.clearScreen();
            setMessage(message);
            putString(room, startCol, roomRow);
            screen.refresh();
        } catch (IOException e) {

        }
    }

/**
Obtains input from the user and returns it as a char.  Converts arrow
keys to the equivalent movement keys in rogue.
@return the ascii value of the key pressed by the user
**/
    public char getInput() {
        KeyStroke keyStroke = null;
        char returnChar;
        keyStroke = getInput2();
        if (keyStroke.getKeyType() == KeyType.ArrowDown) {
            returnChar = Rogue.DOWN;  //constant defined in rogue
        } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
            returnChar = Rogue.UP;
        } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
            returnChar = Rogue.LEFT;
        } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
            returnChar = Rogue.RIGHT;
        } else {
            returnChar = keyStroke.getCharacter();
        }
        return returnChar;
    }

    /**
     * @return keyStroke
     */
    public KeyStroke getInput2() {
        KeyStroke keyStroke = null;
        while (keyStroke == null) {
            try {
                keyStroke = screen.pollInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return keyStroke;
    }
    /**
     * @param userInput
     * @param message
     * @param theGame
     * @param theGameUI
     * @throws ImpossiblePositionException
     */
    public static void userInput(char userInput, String message, Rogue theGame, WindowUI theGameUI)
        throws ImpossiblePositionException {
        JPanel newPanel = new JPanel();
        newPanel.add(myList);
        contentPane.add(newPanel, BorderLayout.EAST);
        while (userInput != 'q') {
            userWhile(userInput, theGame, theGameUI, message);
        }
    }
    /**.
     *@param userInput
     *@param theGame
     *@param theGameUI
     *@param message
     *@throws ImpossiblePositionException
     */
    public static void userWhile(char userInput, Rogue theGame, WindowUI theGameUI, String message)
        throws ImpossiblePositionException {
        int removal = 0;
        Item removeItem = new Item();
        int check = 0;
        userInput = theGameUI.getInput();
        if (userInput == 'e') {
            String input = JOptionPane.showInputDialog(null, "Enter an item u want to eat:  ");
            checkForEat(theGame, removal, removeItem, check, input);
        }
        if (userInput == 'w') {
            String input = JOptionPane.showInputDialog(null, "Enter an item u want to wear:  ");
            checkForWear(theGame, check, input);
        }
        if (userInput == 't') {
            String input = JOptionPane.showInputDialog(null, "Enter an item u want to toss:  ");
            checkForToss(theGame, removal, removeItem, check, input);
        }
        userInput2(userInput, message, theGame, theGameUI);
    }
    /**.
     *@param theGame
     *@param removal
     *@param removeItem
     *@param check
     *@param input
     *@throws ImpossiblePositionException
    */
    public static void checkForToss(Rogue theGame, int removal, Item removeItem, int check, String input)
        throws ImpossiblePositionException {
        for (Item it : theGame.getInventory()) {
            if (it.getName().equals(input)) {
                if (it.getType().equals("SmallFood") || it.getType().equals("Potion")) {
                    check = 1;
                    removal = 1;
                    removeItem = it;
                    JOptionPane.showMessageDialog(null, "Description: " + it.getDescription() + ". "
                        + g.getPlayer().getName() + " tossed: " + input);
                } else {
                    JOptionPane.showMessageDialog(null, g.getPlayer().getName() + " cannot toss: " + input);
                }
            }
        }
        checkToss(check, removal, removeItem, input, theGame);
    }
    /**.
     *@param check
     *@param removal
     *@param removeItem
     *@param input
     *@param theGame
     *@throws ImpossiblePositionException
     */
    public static void checkToss(int check, int removal, Item removeItem, String input, Rogue theGame)
        throws ImpossiblePositionException {
        if (check == 0) {
            JOptionPane.showMessageDialog(null, "not in inventory: " + input);
        }
        if (removal == 1) {
            theGame.removeItems(removeItem);
            theGame.tossTheItem(removeItem);
            myList.setListData(g.makeMoveInventory());
        }
    }
    /**.
     *@param theGame
     *@param check
     *@param input
     */
    public static void checkForWear(Rogue theGame, int check, String input) {
        for (Item it : theGame.getInventory()) {
            if (it.getName().equals(input)) {
                if (it.getType().equals("Clothing") || it.getType().equals("Ring")) {
                    check = 1;
                    it.setName(it.getName() + "(Equipped)");
                    JOptionPane.showMessageDialog(null, "Description:" + it.getDescription() + ". "
                        + g.getPlayer().getName() + " wore: " + input);
                    myList.setListData(g.makeMoveInventory());
                } else {
                    JOptionPane.showMessageDialog(null, g.getPlayer().getName() + " cannot wear: " + input);
                }
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(null, "not in inventory: " + input);
        }
    }
    /**.
     *@param theGame
     *@param removal
     *@param removeItem
     *@param check
     *@param input
     */
    public static void checkForEat(Rogue theGame, int removal, Item removeItem, int check, String input) {
        for (Item it : theGame.getInventory()) {
            if (it.getName().equals(input)) {
                if (it.getType().equals("Food") || it.getType().equals("Potion") || it.getType().equals("SmallFood")) {
                    check = 1;
                    removal = 1;
                    removeItem = it;
                    JOptionPane.showMessageDialog(null, "Description: " + it.getDescription()
                        + ". " + g.getPlayer().getName() + " ate: " + input);
                } else {
                    JOptionPane.showMessageDialog(null, g.getPlayer().getName() + " cannot eat: " + input);
                }
            }
        }
        checkEat(check, removal, removeItem, input, theGame);
    }
    /**.
     *@param check
     *@param removal
     *@param removeItem
     *@param input
     *@param theGame
     */
    public static void checkEat(int check, int removal, Item removeItem, String input, Rogue theGame) {
        if (check == 0) {
            JOptionPane.showMessageDialog(null, "not in inventory: " + input);
        }
        if (removal == 1) {
            theGame.removeItems(removeItem);
            myList.setListData(g.makeMoveInventory());
        }
    }
    /**
     * @param userInput
     * @param message
     * @param theGame
     * @param theGameUI
     */
    public static void userInput2(char userInput, String message, Rogue theGame, WindowUI theGameUI) {
        try {
            message = theGame.makeMove(userInput);
            if (message.contains("Picked up")) {
                myList.setListData(g.makeMoveInventory());
                inventory = g.getInventory();
            }
            theGameUI.draw(message, theGame.getNextDisplay());
            throw new InvalidMoveException("invalid");
        } catch (InvalidMoveException badMove) {
            message = "I didn't understand what you meant, please enter a command";
            theGameUI.setMessage(message);
        }
    }
    /**
    The controller method for making the game logic work.
    @param args command line parameters
    @throws ImpossiblePositionException
    **/
    public static void main(String[] args) throws ImpossiblePositionException {
        char userInput = 'h';
        String message;
        String configurationFileLocation = "fileLocations.json";
        RogueParser parser = new RogueParser(configurationFileLocation);
        WindowUI theGameUI = new WindowUI();
        g = new Rogue(parser);
        Player thePlayer = new Player("Judi");
        g.setPlayer(thePlayer);
        exampleLabel.setText("player: " + g.getPlayer().getName());
        message = "Welcome to my Rogue game";
        theGameUI.draw(message, g.getNextDisplay());
        theGameUI.setVisible(true);
        userInput(userInput, message, g, theGameUI);
    }
}
