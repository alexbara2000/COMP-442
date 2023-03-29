% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %reading variable from the keyboard
           addi r1, r0, buf
           sw -8(r14),r1
           jl r15,getstr
           jl r15,strint
           sw x(r0),r13 


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t2(r0),r9


           % processing: t3 = t1 * t2
           lw r1,t1(r0)
           lw r2,t2(r0)
           mul r3,r1,r2
           sw t3(r0),r3


           % processing: t4 = x + t3
           lw r1,x(r0)
           lw r2,t3(r0)
           add r3,r1,r2
           sw t4(r0),r3


           % processing: put(t4)
           lw r1,t4(r0)
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
% space for variable x
x       res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for t1 * t2
t3         res 4
% space for x + t3
t4         res 4
