*The assembler is made up of 3 seperate class files

*data_list

data_list is a class consisting of various instruction set which are contained in HashMaps.
These instructions are used to convert the assembly code into its hack binary form.
The instructions are made up of A-instruction and C-instruction

#Bin
Bin is a simple decimal to binary convertor that is used to convert the decimal values of some instructions or line numbers
to its binary form

#hack_assembly
This is the main class in which all the conversion of code from its assembly form to binary form takes place
The hack_assembly class has access to both the data_list and Bin classes to perform necessary operations
A-instruction and C-instruction are treated seperatly in different functions

#Max.asm
This is an assembly program to find the bigger value between two different values

#Max.hack
This is the hack-binary form of the Max.asm code which has been obtained from this assembler
