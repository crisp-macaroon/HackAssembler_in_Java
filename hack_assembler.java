package src;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

class hack_assembler{
	public data_list data = new data_list();             // calling the pre-defined data list and instruction set
	ArrayList<String> bin =  new ArrayList<>();			// List to store the binary code
	HashMap<String,String> table = new HashMap<>();		// hashmap to store symbols not defined in symbol table
	int l_cnt = 0;										// line counter
	int count = 16;
	
	void A_inst(String l) {								// A-instruction
		StringBuilder sb = new StringBuilder(l);
		String label = "";
		sb.deleteCharAt(0);
			if (data.predif.containsKey(sb.toString())){              // for predefined A-instructions in symbol table
				label = "0" + Bin.d2b(data.predif.get(sb.toString()));
			}
			else if(Pattern.compile("[A-Za-z]+").matcher(sb.toString()).find()){  // for explicitly defined A-instructions
				if(table.containsKey(sb.toString())){

					label = "0" + table.get(sb.toString());
				}
				else {
					label = "0" + Bin.d2b(String.valueOf(count));
					table.put(sb.toString(),Bin.d2b(String.valueOf(count)));
					count += 1;
				}
			}
			else if(sb.toString().matches("[0-910-24576]+")){      // for A-instructions containing only numbers
				label = "0" + Bin.d2b(sb.toString());
			}
		bin.add(label);          // adding the binary to the arraylist
	}

	public void C_inst(String C){         // C-instruction
		String s = "111";
		String dest;
		String jump = "null";
		String label = "";
		if(C.contains("=")){                          // for C-instruction of the form {DEST = COMP;JMP} (conditional branching)
			String[] c = C.split("=");
			if(data.comp.containsKey(c[1])) {
				label = s + data.comp.get(c[1]) + data.dest.get(c[0]) + data.jump.get(jump);
			}
		}
		else{                                        // for C-instructions without a destination (unconditional branching)
			String[] c = C.split(";");
			dest = "null";
			label = s + data.comp.get(c[0]) + data.dest.get(dest) + data.jump.get(c[1]);
		}
		bin.add(label);     // adding C-instruction to the arraylist
	}



	 void firstpass() throws IOException {                          		// first pass
		FileReader read_file = new FileReader("Max.asm");  		// inputting the assembly file
		BufferedReader bfr1 = new BufferedReader(read_file);	   			// line reader class
		String line;
		String c;
		String[] l ;
        // first pass 
		while((line = bfr1.readLine()) != null) {							// checks for END OF FILE
			if(!line.isEmpty() && line.charAt(0) != '/') {					// checks for empty and commented line
				if(Pattern.compile("^[(]").matcher(line).find()){		// checks for labels
					c = line.replaceAll("[(]|[)]", "");	// remove brackets
					c = c.replaceAll(" ","");
					l = c.split("/");							        // splitting line having comments
					table.put(l[0], Bin.d2b(String.valueOf(l_cnt)));        // putting the label with line number into symbol table
				}
				else
					l_cnt +=1;
			}
		}
		bfr1.close();
	}
		void secondpass() throws IOException{                               // second pass
		FileReader sec_read = new FileReader("Max.asm");			// same as first pass
	    BufferedReader bfr2 = new BufferedReader(sec_read);
			String line;
			String[] l;
	        while ((line = bfr2.readLine()) != null) {
	            if(!line.isEmpty() && line.charAt(0) != '/'){
	               line = line.replaceAll(" ","");
	                if(Pattern.compile("^@").matcher(line).find()){  // check for A-instruction
	                    if(line.contains("/")){
	                        l = line.split("/");
							A_inst(l[0]);

	                    }
	                    else{
	                        A_inst(line);
	                    }
	              }
	                   else if(Pattern.compile("^[(]").matcher(line).find()) {     // ignore labels

                    }
	                   else {
	                       if(line.contains("/")){										// check for C-instruction
	                           l = line.split("/");
	                           C_inst(l[0]);
						   }
	                    else 
	                    	C_inst(line);
	                   }
	            	}
	            }
	        bfr2.close();															// closing the stream
			}

			void out() throws IOException {
				FileWriter fr = new FileWriter("max.hack");           // file writer class to write output file
				for (String s : bin) {
					fr.write(s + "\n");
				}
				fr.close();
			}
	public static void main(String[] args) throws IOException{
		hack_assembler ob1 = new hack_assembler();						   // object creation
		ob1.firstpass();												   // calling functions
		ob1.secondpass();     // input assembly file
		ob1.out();		   // output binary code file
	}
}

	         
	
	
