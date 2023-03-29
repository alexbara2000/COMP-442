% start of function
func

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t2(r0),r9


           % processing: t3 = t1 * t2
           lw r1,t1(r0)
           lw r2,t2(r0)
           mul r3,r1,r2
           sw t3(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t4(r0),r9


           % processing: t5 = t3 + t4
           lw r1,t3(r0)
           lw r2,t4(r0)
           add r3,r1,r2
           sw t5(r0),r3


           %assigning values
           lw r9,t5(r0)
           sw y(r0),r9


           % processing: t6 = y + y
           lw r1,y(r0)
           lw r2,y(r0)
           add r3,r1,r2
           sw t6(r0),r3


           % processing: t7 = y + t6
           lw r1,y(r0)
           lw r2,t6(r0)
           add r3,r1,r2
           sw t7(r0),r3


           %putting return value of function
           lw r1,t7(r0)
           sw fnres(r0), r1
           jr r15
           jr r15
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           % calling function: func
           jl r15,func

           % getting return value
           lw r1,fnres(r0)
           sw t8(r0),r1 


           %assigning values
           lw r9,t8(r0)
           sw x(r0),r9


           % processing: put(x)
           lw r1,x(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           sub r6,r6,r6
           addi r6,r6,10
           putc r6
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable y
y       res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for t1 * t2
t3         res 4
% space for variable t4
t4      res 4
% space for t3 + t4
t5         res 4
% space for y + y
t6         res 4
% space for y + t6
t7         res 4
% space for variable x
x       res 4
% space for return value of function: func
t8         res 4
