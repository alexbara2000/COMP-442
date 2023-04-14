% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t2(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t3(r0),r9


           % processing: t4 = t2 * t3
           lw r1,t2(r0)
           lw r2,t3(r0)
           mul r3,r1,r2
           sw t4(r0),r3


           % processing: t5 = t1 + t4
           lw r1,t1(r0)
           lw r2,t4(r0)
           add r3,r1,r2
           sw t5(r0),r3


           %assigning values
           lw r9,t5(r0)
           sw y(r0),r9


           %reading variable from the keyboard
           addi r1, r0, buf
           sw -8(r14),r1
           jl r3,getstr
           jl r12,strint
           sw x(r0),r12 


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t6(r0),r9


           % processing: t7 = y + t6
           lw r1,y(r0)
           lw r2,t6(r0)
           add r3,r1,r2
           sw t7(r0),r3


           % processing: t8 = x > t7
           lw r1,x(r0)
           lw r2,t7(r0)
           cgt r3,r1,r2
           sw t8(r0),r3


%checking if condition
           lw r1, t8(r0)
           bz r1, statblock2
statblock1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t9(r0),r9


           % processing: t10 = x + t9
           lw r1,x(r0)
           lw r2,t9(r0)
           add r3,r1,r2
           sw t10(r0),r3


           % processing: put(t10)
           lw r1,t10(r0)
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

%finished doing stat block 1
           j endRel1
statblock2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t11(r0),r9


           % processing: t12 = x + t11
           lw r1,x(r0)
           lw r2,t11(r0)
           add r3,r1,r2
           sw t12(r0),r3


           % processing: put(t12)
           lw r1,t12(r0)
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

endRel1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t13(r0),r9


           %assigning values
           lw r9,t13(r0)
           sw z(r0),r9


%checking while loop condition
gowhile1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t14(r0),r9


           % processing: t15 = z <= t14
           lw r1,z(r0)
           lw r2,t14(r0)
           cle r3,r1,r2
           sw t15(r0),r3

           lw r1, t15(r0)
           bz r1, endRel2
statblock3

           % processing: put(z)
           lw r1,z(r0)
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

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t16(r0),r9


           % processing: t17 = z + t16
           lw r1,z(r0)
           lw r2,t16(r0)
           add r3,r1,r2
           sw t17(r0),r3


           %assigning values
           lw r9,t17(r0)
           sw z(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel2
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable x
x       res 4
% space for variable y
y       res 4
% space for variable z
z       res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for t2 * t3
t4         res 4
% space for t1 + t4
t5         res 4
% space for variable t6
t6      res 4
% space for y + t6
t7         res 4
% space for x < t7
t8         res 4
% space for variable t9
t9      res 4
% space for x + t9
t10        res 4
% space for variable t11
t11     res 4
% space for x + t11
t12        res 4
% space for variable t13
t13     res 4
% space for variable t14
t14     res 4
% space for z < t14
t15        res 4
% space for variable t16
t16     res 4
% space for z + t16
t17        res 4
