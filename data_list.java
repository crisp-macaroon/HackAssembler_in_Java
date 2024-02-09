package src;

import java.util.HashMap;               // symbol table creation (contains all the predefined instruction set)
class data_list 
{
	HashMap<String,String> predif;
	HashMap<String,String> dest;
	HashMap<String,String> jump;
	HashMap<String,String> comp;

                                      // creating predefined symbols and tokens
	data_list(){
	predif = new HashMap<>();
	predif.put("R0","0");
	predif.put("R1", "1");
	predif.put("R2", "2");
	predif.put("R3", "3");
	predif.put("R4", "4");
	predif.put("R5", "5");
	predif.put("R6", "6");
	predif.put("R7", "7");
	predif.put("R8", "8");
	predif.put("R9", "9");
	predif.put("R10", "10");
	predif.put("R11", "11");
	predif.put("R12", "12");
	predif.put("R13", "13");
	predif.put("R14", "14");
	predif.put("R15", "15");
	predif.put("SCREEN", "16384");
	predif.put("KBD", "24576");
	predif.put("SP", "0");
	predif.put("LCL", "1");
	predif.put("ARG", "2");
	predif.put("THIS", "3");
	predif.put("THAT", "4");
	predif.put("STOP", "18");
	predif.put("END", "22"); 
	
    dest = new HashMap<>();           // the destinations
    
   dest.put("null", "000");
   dest.put("M", "001");
   dest.put("D", "010");
   dest.put("MD", "011");
   dest.put("A", "100");
   dest.put("AM", "101");
   dest.put("AD", "110");
   dest.put("AMD", "111");
   
   jump= new HashMap<>();          // the jump operations
   
   jump.put("null", "000");
   jump.put("JGT", "001");
   jump.put("JEQ", "010");
   jump.put("JGE", "011");
   jump.put("JLT", "100");
   jump.put("JNE", "101");
   jump.put("JLE", "110");
   jump.put("JMP", "111");
   
   comp= new HashMap<>();        // the computations
   
   comp.put("0", "0101010");
   comp.put("1", "0111111");
   comp.put("-1", "0111010");
   comp.put("D", "0001100");
   comp.put("A", "0110000");
   comp.put("!D", "0001101");
   comp.put("!A", "0110001");
   comp.put("-D", "0001111");
   comp.put("-A", "0110011");
   comp.put("D+1", "0011111");
   comp.put("A+1", "0110111");
   comp.put("D-1", "0001110");
   comp.put("A-1", "0110010");
   comp.put("D+A", "0000010");
   comp.put("A+D", "0000010");
   comp.put("D-A", "0010011");
   comp.put("A-D", "0000111");
   comp.put("D&A", "0000000");
   comp.put("D|A", "0010101");
   
   comp.put("M", "1110000");
   comp.put("!M", "1110001");
   comp.put("-M", "1110011");
   comp.put("M+1", "1110111");
   comp.put("M-1", "1110010");
   comp.put("D+M", "1000010");
   comp.put("M+D", "1000010");
   comp.put("D-M", "1010011");
   comp.put("M-D", "1000111");
   comp.put("D&M", "1000000");
   comp.put("D|M", "1010101");


}
	public String get(String str1,String str2){       // return value based on key
        return switch (str1) {
            case "dest": yield dest.get(str2);
            case "predif": yield predif.get(str2);
            case "jump": yield jump.get(str2);
            case "comp": yield comp.get(str2);
            default: yield null;
        };
    }
}
