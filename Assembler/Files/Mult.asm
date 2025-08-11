@R1
D=M
@count
M=D

@R2
M=0

(LOOP)
@count
D=M
@END
D;JEQ

@R0
D=M
@R2
M=D+M

@count
M=M-1

@LOOP
0;JMP

(END)
@END
0;JMP