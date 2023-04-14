% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t2(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t3(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t4(r0),r9


           % processing: t5 = t3 - t4
           lw r1,t3(r0)
           lw r2,t4(r0)
           sub r3,r1,r2
           sw t5(r0),r3


           % processing: t6 = t2 + t5
           lw r1,t2(r0)
           lw r2,t5(r0)
           add r3,r1,r2
           sw t6(r0),r3


           % processing: t7 = t1 + t6
           lw r1,t1(r0)
           lw r2,t6(r0)
           add r3,r1,r2
           sw t7(r0),r3


           %assigning values
           lw r9,t7(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t8(r0),r9


           % processing: t9 = x - t8
           lw r1,x(r0)
           lw r2,t8(r0)
           sub r3,r1,r2
           sw t9(r0),r3


           % processing: t10 = x + t9
           lw r1,x(r0)
           lw r2,t9(r0)
           add r3,r1,r2
           sw t10(r0),r3


           %assigning values
           lw r9,t10(r0)
           sw y(r0),r9


           % processing: put(y)
           lw r1,y(r0)
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
% space for variable x
x       res 4
% space for variable y
y       res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for t3 - t4
t5         res 4
% space for t2 + t5
t6         res 4
% space for t1 + t6
t7         res 4
% space for variable t8
t8      res 4
% space for x - t8
t9         res 4
% space for x + t9
t10        res 4
