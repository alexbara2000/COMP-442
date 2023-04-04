% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t2(r0),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,0
           lw r9,t2(r0)
           sw x(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,4
           sw t3(r0),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,4
           lw r9,t3(r0)
           sw x(r8),r9


           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,0
           lw r1,x(r9)
           sw t4(r0),r1



           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,4
           lw r1,x(r9)
           sw t5(r0),r1



           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,4
           lw r1,x(r9)
           sw t6(r0),r1



           % processing: t7 = t5 * t6
           lw r1,t5(r0)
           lw r2,t6(r0)
           mul r3,r1,r2
           sw t7(r0),r3


           % processing: t8 = t4 + t7
           lw r1,t4(r0)
           lw r2,t7(r0)
           add r3,r1,r2
           sw t8(r0),r3


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,t8(r0)
           sw x(r8),r9


           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,8
           lw r1,x(r9)
           sw t9(r0),r1



           % processing: put(t9)
           lw r1,t9(r0)
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
x       res 12
% space for variable y
y       res 60
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for array value
t4         res 4
% space for array value
t5         res 4
% space for array value
t6         res 4
% space for t5 * t6
t7         res 4
% space for t4 + t7
t8         res 4
% space for array value
t9         res 4
