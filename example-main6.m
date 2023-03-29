% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t2(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,64
           sw t3(r0),r9


           %assigning values
           lw r1,t2(r0)
           muli r2,r1,4
           lw r9,t3(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t4(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,34
           sw t5(r0),r9


           %assigning values
           lw r1,t4(r0)
           muli r2,r1,4
           lw r9,t5(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t6(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,25
           sw t7(r0),r9


           %assigning values
           lw r1,t6(r0)
           muli r2,r1,4
           lw r9,t7(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t8(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,12
           sw t9(r0),r9


           %assigning values
           lw r1,t8(r0)
           muli r2,r1,4
           lw r9,t9(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,4
           sw t10(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,22
           sw t11(r0),r9


           %assigning values
           lw r1,t10(r0)
           muli r2,r1,4
           lw r9,t11(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t12(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,11
           sw t13(r0),r9


           %assigning values
           lw r1,t12(r0)
           muli r2,r1,4
           lw r9,t13(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t14(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,90
           sw t15(r0),r9


           %assigning values
           lw r1,t14(r0)
           muli r2,r1,4
           lw r9,t15(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t16(r0),r9


           %assigning values
           lw r9,t16(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t17(r0),r9


           %assigning values
           lw r9,t17(r0)
           sw i(r0),r9


%checking while loop condition
gowhile1

           % processing: t18 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t18(r0),r3

           lw r1, t18(r0)
           bz r1, endRel1
statblock1

           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t19(r0),r9



           % processing: put(t19)
           lw r1,t19(r0)
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


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t20(r0),r9


           % processing: t21 = i + t20
           lw r1,i(r0)
           lw r2,t20(r0)
           add r3,r1,r2
           sw t21(r0),r3


           %assigning values
           lw r9,t21(r0)
           sw i(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel1
           hlt
% space for variable buffer
buf     res 20
% space for variable arr
arr     res 28
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for variable t5
t5      res 4
% space for variable t6
t6      res 4
% space for variable t7
t7      res 4
% space for variable t8
t8      res 4
% space for variable t9
t9      res 4
% space for variable t10
t10     res 4
% space for variable t11
t11     res 4
% space for variable t12
t12     res 4
% space for variable t13
t13     res 4
% space for variable t14
t14     res 4
% space for variable t15
t15     res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable t16
t16     res 4
% space for variable t17
t17     res 4
% space for i < n
t18        res 4
% space for array value
t19        res 4
% space for variable t20
t20     res 4
% space for i + t20
t21        res 4
