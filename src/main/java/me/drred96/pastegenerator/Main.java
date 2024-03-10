package me.drred96.pastegenerator;

import me.drred96.pastegenerator.paste.Paste;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Specify a file...");
            return;
        }

        try {
            Paste paste = new Paste();
            paste.load(args[0]);
            paste.write("paste.txt");

        } catch (Exception exception) {
            System.err.printf("%s: %s\n", exception.getClass().getName(), exception.getMessage());
            exception.printStackTrace();
//            for (String arg : args) {
//                if (arg.equalsIgnoreCase("--stacktrace")) {
//                    exception.printStackTrace();
//                    break;
//                }
//            }
        }
    }

}
