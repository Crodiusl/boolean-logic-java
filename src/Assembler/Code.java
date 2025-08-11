package Assembler;

import java.util.HashMap;

public class Code {
    HashMap<String, String> comp = new HashMap<>();
    HashMap<String, String> dest = new HashMap<>();
    HashMap<String, String> jump = new HashMap<>();

    public Code() {
        fillTables();
    }

    public String getBinary(String line) {
        int num = Integer.parseInt(line);
        StringBuilder binary = new StringBuilder();

        for (int i = 15; i >= 0; i--) {
            int a = (num >> i) & 1;
            binary.append(a);
        }

        return binary.toString();
    }

    public String getComp(String compString) {
        return comp.get(compString);
    }

    public String getDest(String destString) {
        return dest.get(destString);
    }

    public String getJump(String jumpString) {
        return jump.get(jumpString);
    }


    private void fillTables() { // premade table
        comp.put("0", "101010");
        comp.put("1", "111111");
        comp.put("-1", "111010");

        comp.put("D", "001100");
        comp.put("A", "110000");

        comp.put("!D", "001101");
        comp.put("!A", "110001");

        comp.put("-D", "001111");
        comp.put("-A", "110011");

        comp.put("D+1", "011111");
        comp.put("A+1", "110111");
        comp.put("D-1", "001110");
        comp.put("A-1", "110010");

        comp.put("D+A", "000010");
        comp.put("D-A", "010011");
        comp.put("A-D", "000111");

        comp.put("D&A", "000000");
        comp.put("D|A", "010101");

        comp.put("M", "110000");
        comp.put("!M", "110001");
        comp.put("-M", "110011");
        comp.put("M+1", "110111");
        comp.put("M-1", "110010");
        comp.put("D+M", "000010");
        comp.put("D-M", "010011");
        comp.put("M-D", "000111");
        comp.put("D&M", "000000");
        comp.put("D|M", "010101");


        dest.put("null", "000");
        dest.put("M", "001");
        dest.put("D", "010");
        dest.put("DM", "011");
        dest.put("MD", "011");

        dest.put("A", "100");
        dest.put("AM", "101");
        dest.put("MA", "101");

        dest.put("AD", "110");
        dest.put("DA", "110");

        dest.put("ADM", "111");
        dest.put("AMD", "111");
        dest.put("DMA", "111");
        dest.put("DAM", "111");
        dest.put("MAD", "111");
        dest.put("MDA", "111");


        jump.put("null", "000");
        jump.put("JGT", "001");
        jump.put("JEQ", "010");
        jump.put("JGE", "011");
        jump.put("JLT", "100");
        jump.put("JNE", "101");
        jump.put("JLE", "110");
        jump.put("JMP", "111");

    }
}
