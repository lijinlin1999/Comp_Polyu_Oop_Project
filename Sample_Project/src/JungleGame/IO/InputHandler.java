/*
 A class implemented to handle input using singleton pattern.
 To get a string, call getInputHandler().getString(), and input the string via console
 To get a command object, call getInputHandler().getCommand(), and input the string via console
 The method getCommand() handles multiple spaces or lack of spaces between tokens automatically,
 and coordination representation is case insensitive. For example:
     SaveExample.txt
     LoadExample.txt
     C1->C5
     C1   ->   c5
     etc., are valid input and will get valid command as return. Keywords are still case sensitive.
 */

package JungleGame.IO;

import JungleGame.Game.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private Scanner s;
    private StringBuffer sb;
    private volatile static InputHandler handler = null;

    // lazy initialization with one static field containing the only InputHandler
    private InputHandler() {
        s = new Scanner(System.in);
        sb = new StringBuffer();
    }

    // check if the handler is null, then create or use the current one to return to caller. use synchronization to maintain thread-safe
    public static InputHandler getInputHandler() {
        if (handler == null) {
            synchronized (InputHandler.class) {
                if (handler == null) {
                    handler = new InputHandler();
                }
            }
        }
        return handler;
    }

    // tokenize the command from a string to array of strings
    private String[] Tokenize() {
        List<String> tokens = new ArrayList<String>();
        String token = NextToken();

        // iteratively get all tokens
        while (token != null) {
            tokens.add(token);
            token = NextToken();
        }

        // store tokens as an array and return
        String[] tokenArr = new String[tokens.size()];
        for (int i = 0; i < tokens.size(); i++) {
            tokenArr[i] = tokens.get(i);
        }
        return tokenArr;
    }

    // return a 2-item array indicating the coordinate [x, y]
    private static int[] getCoordinate(String coord) {
        return new int[] {Math.min(coord.charAt(0) - 'a', coord.charAt(0) - 'A'), coord.charAt(1) - '1'};
    }

    // identify the command type based on token types
    private static int typeIdentify(String[] tokens) {
        if (tokens.length == 2 && tokens[0].equals(Keyword.Key_Load.text)) {
            return 0;
        }

        else if (tokens.length == 2 && tokens[0].equals(Keyword.Key_Save.text)) {
            return 1;
        }

        else if (tokens.length == 3 && Keyword.isCoordinate(tokens[0]) && tokens[1].equals(Keyword.Key_Pointer.text) && Keyword.isCoordinate(tokens[2])) {
            return 2;
        }

        else if (tokens.length == 1 && tokens[0].equals(Keyword.Key_Exit.text)) {
            return 3;
        }

        else return -1;
    }


    // public method to get a command object based on input string
    public Command getCommand() {

        // clear the string buffer, then get a line of command
        sb.delete(0, sb.length());
        sb.append(s.nextLine());

        // tokenize the command, and identify command types
        String[] tokens = Tokenize();
        sb.delete(0, sb.length());

        // return command object based on command types
        // 0: load command
        // 1: save command
        // 2: game command
        switch (typeIdentify(tokens)) {
            case 0: return new LoadCommand(tokens[1]);
            case 1: return new SaveCommand(tokens[1]);
            case 2: return new GameCommand(getCoordinate(tokens[0])[0], getCoordinate(tokens[0])[1],
                                            getCoordinate(tokens[2])[0], getCoordinate(tokens[2])[1]);
            case 3: return new ExitCommand();
            case -1: return new InvalidCommand();
            default: return new InvalidCommand();
        }

    }

    // return the next token stored in StringBuffer
    private String NextToken() {

        // return a null token if string buffer is already empty
        if (sb.length() == 0) {
            return null;
        }

        // pop a char from string buffer, continue if found a space
        char next = sb.charAt(0);
        sb.deleteCharAt(0);
        while (sb.length() > 0 && next == ' ') {
            next = sb.charAt(0);
            sb.deleteCharAt(0);
        }
        if (next == ' ' && sb.length() == 0) {
            return null;
        }

        // a workplace for the token
        StringBuilder temp = new StringBuilder();

        // get characters until met a space
        while (next != ' ') {
            temp.append(next);
            if (sb.length() == 0) {
                break;
            }
            next = sb.charAt(0);
            sb.deleteCharAt(0);
        }

        // return the token
        return temp.toString();
    }


    // get a string from input handler
    public String getString() {
        sb.append(s.nextLine());
        String message = sb.toString();
        sb.delete(0, sb.length());
        return message;
    }
}



// an enumeration class called keyword, containing keywords used in game command
enum Keyword {
    Key_Move("Move"), Key_Save("Save"), Key_Load("Load"), Key_Pointer("->"), Key_Exit("Exit");
    public final String text;

    private Keyword(String text) {
        this.text = text;
    }

    // test whether a character is letter or not
    public static boolean isLetter(char letter) {
        return (letter >= 'a' && letter <= 'z' || letter >= 'A' && letter <= 'Z');
    }

    // test whether a character is digit or not
    public static boolean isDigit(char digit) {
        return (digit >= '0' && digit <= '9');
    }

    // test whether a string is a complete keyword
    public static boolean isKeyword(String text) {
        return (text.equals(Key_Move.text) || text.equals(Key_Save.text) || text.equals(Key_Load.text) || text.equals(Key_Pointer.text));
    }

    // test whether text is valid coordinate
    public static boolean isCoordinate(String text) {
        return (text.length() == 2 && isLetter(text.charAt(0)) && isDigit(text.charAt(1)));
    }

}