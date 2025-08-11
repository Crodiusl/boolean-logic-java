package Assembler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Parser {

    private String outputFileName;
    private BufferedReader br;
    private BufferedWriter bw;

    private String line;
    private final Code code;

    private final String constOnes = "111";
    private final String constZeros = "000";

    private int i = 16; // first custom variables, then i++
    private final HashMap<String, Integer> marks; // all known marks (consts, loops or already known vars)
    private String a; // bit that indicates from where to get data (if 0 then from register A; if 1 from register M)

    public static void main(String[] args) {
        new Parser("src/Assembler/Files/Mult.asm");
    }

    public Parser(String fileName) {
        /*
         * For most part of it we ignore human factor, so it is possible it will give wrong results
         * if any typo or bad syntaxis found
         * if comment on the same line as instruction - it will be wrong result
         * spaces should be considered as a new line, but there are no implementation of this in code
         * Maybe I will fix it later */

        code = new Code();
        marks = new HashMap<>();

        getOutputName(fileName); // extrcting file name from hardcoded path

        try {
            br = new BufferedReader(new FileReader(fileName));
            bw = new BufferedWriter(new FileWriter("src/Assembler/Files/" + outputFileName));

            findMarks(); // first loop, we add all marks
            br = new BufferedReader(new FileReader(fileName));

            readFile(); // we read again and translate to binary

            br.close();
            bw.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void readFile() {

        try {

            while ((line = br.readLine()) != null) { // reading line and checking if its null
                line = line.trim();

                // ignore comments, marks and empty lines
                if (line.isEmpty() || line.isBlank() || line.charAt(0) == '/' || line.charAt(0) == '(') {
                    continue;
                }

                parseLine(line);
            }

        } catch (java.io.IOException e) {
            System.out.println(e);
        }

    }

    private void findMarks() {
        // const
        marks.put("SP", 0);
        marks.put("LCL", 1);
        marks.put("ARG", 2);
        marks.put("THIS", 3);
        marks.put("THAT", 4);
        marks.put("SCREEN", 16384);
        marks.put("KBD", 24576);

        // if line is A or C instruction then we increase this number
        // and use it as address
        int lineNum = 0;

        try {

            while ((line = br.readLine()) != null) { // reading line and checking if its null

                // ignore comments and empty lines
                line = line.trim();
                if (line.isEmpty() || line.isBlank() || line.charAt(0) == '/') {
                    continue;
                }
                if (line.charAt(0) == '(') {

                    line = line.substring(1, line.length() - 1); //removing round brackets

                    marks.put(line, lineNum);
                    continue; // skip lineNum increase, becuse it is not A or C instruction
                }

                lineNum++;
            }

            br.close();

        } catch (java.io.IOException e) {
            System.out.println(e);
        }


    }

    private void parseLine(String line) {
        String newLine;
        String[] parts;

        char second = line.charAt(1);

        if (line.charAt(0) == '@') {
            newLine = code.getBinary(symbol(line)); // translate A instruction to binary

        } else if (second == ';') { // translate C if statement
            parts = line.split(";");
            newLine = instructionCIf(parts);

        } else {
            parts = line.split("=");
            newLine = instructionC(parts);
        }

        write(newLine);

    }

    private String symbol(String line) {
        String temp = line.substring(1); // without "@"

        if (marks.containsKey(temp)) { // if marked from first loop
            return marks.get(temp) + "";
        } else {
            if (isNumber(temp)) {
                return temp;
            } else if (temp.length() <= 3 && temp.charAt(0) == 'R' && isNumber(temp.substring(1))) {
                return temp.substring(1);
            } else {
                marks.put(temp, i);
                return (i++) + "";
            }
        }
    }

    private boolean isNumber(String line) {
        for (char c : line.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private String instructionC(String[] parts) { // C statement
        String dest = parts[0];
        String comp = parts[1];
        // jump is always const 0 here
        a = parts[1].contains("M") ? "1" : "0";

        return constOnes + a + code.getComp(comp) + code.getDest(dest) + constZeros;
    }

    private String instructionCIf(String[] parts) { // C if statement
        String comp = parts[0];
        String jump = parts[1];
        // dest is always const 0 here
        a = "0";

        return constOnes + a + code.getComp(comp) + constZeros + code.getJump(jump);
    }


    private void write(String newLine) {
        try {
            bw.write(newLine);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void getOutputName(String fileName) {
        String[] words = fileName.split("/");
        String name = words[words.length - 1];
        outputFileName = name.substring(0, name.length() - 4) + ".hack";
    }


}
