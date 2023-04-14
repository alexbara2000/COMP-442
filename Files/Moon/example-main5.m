% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %assigning values
           lw r9,t1(r0)
           sw x(r0),r9


           % processing: t2 = not x
           lw r1,x(r0)
           bnz r1,zero1
           addi r2,r0,1
           sw t2(r0),r2 
           j endRel2

zero1     sw t2(r0),r0 
endRel2   

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t3(r0),r9


           % processing: t4 = t2 == t3
           lw r1,t2(r0)
           lw r2,t3(r0)
           ceq r3,r1,r2
           sw t4(r0),r3


%checking if condition
           lw r1, t4(r0)
           bz r1, statblock2
statblock1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t5(r0),r9


           %assigning values
           lw r9,t5(r0)
           sw x(r0),r9


%finished doing stat block 1
           j endRel1
statblock2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t6(r0),r9


           %assigning values
           lw r9,t6(r0)
           sw x(r0),r9


endRel1

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
% space for variable x
x       res 4
% space for variable t1
t1      res 4
% space for not x
t2         res 4
% space for variable t3
t3      res 4
% space for t2 == t3
t4         res 4
% space for variable t5
t5      res 4
% space for variable t6
t6      res 4
