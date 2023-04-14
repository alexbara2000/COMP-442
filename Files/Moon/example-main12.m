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


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t2(r0),r9


           %assigning values
           lw r9,t2(r0)
           sw y(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t3(r0),r9


           %assigning values
           lw r9,t3(r0)
           sw jvalue(r0),r9


%checking while loop condition
gowhile1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t4(r0),r9


           % processing: t5 = x - t4
           lw r1,x(r0)
           lw r2,t4(r0)
           sub r3,r1,r2
           sw t5(r0),r3


           % processing: t6 = y < t5
           lw r1,y(r0)
           lw r2,t5(r0)
           clt r3,r1,r2
           sw t6(r0),r3

           lw r1, t6(r0)
           bz r1, endRel1
statblock1

%checking while loop condition
gowhile2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t7(r0),r9


           % processing: t8 = jvalue < t7
           lw r1,jvalue(r0)
           lw r2,t7(r0)
           clt r3,r1,r2
           sw t8(r0),r3

           lw r1, t8(r0)
           bz r1, endRel2
statblock2

           % processing: put(jvalue)
           lw r1,jvalue(r0)
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
           sw t9(r0),r9


           % processing: t10 = jvalue + t9
           lw r1,jvalue(r0)
           lw r2,t9(r0)
           add r3,r1,r2
           sw t10(r0),r3


           %assigning values
           lw r9,t10(r0)
           sw jvalue(r0),r9


%finished doing stat block going back to top
           j gowhile2

endRel2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1000
           sw t11(r0),r9


           % processing: put(t11)
           lw r1,t11(r0)
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

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t12(r0),r9


           % processing: t13 = y + t12
           lw r1,y(r0)
           lw r2,t12(r0)
           add r3,r1,r2
           sw t13(r0),r3


           %assigning values
           lw r9,t13(r0)
           sw y(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel1
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable x
x       res 4
% space for variable y
y       res 4
% space for variable jvalue
jvalue  res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for x - t4
t5         res 4
% space for y < t5
t6         res 4
% space for variable t7
t7      res 4
% space for jvalue < t7
t8         res 4
% space for variable t9
t9      res 4
% space for jvalue + t9
t10        res 4
% space for variable t11
t11     res 4
% space for variable t12
t12     res 4
% space for y + t12
t13        res 4
