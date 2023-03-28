% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
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
           addi r9,r9,0
           sw t16(r0),r9


           %assigning values in factor
           lw r1,t16(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t17(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t18(r0),r9


           %assigning values in factor
           lw r1,t18(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t19(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t20(r0),r9


           %assigning values in factor
           lw r1,t20(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t21(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t22(r0),r9


           %assigning values in factor
           lw r1,t22(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t23(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,4
           sw t24(r0),r9


           %assigning values in factor
           lw r1,t24(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t25(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t26(r0),r9


           %assigning values in factor
           lw r1,t26(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t27(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t28(r0),r9


           %assigning values in factor
           lw r1,t28(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t29(r0),r9



           % processing: t30 := t27 + t29
           lw r1,t27(r0)
           lw r2,t29(r0)
           add r3,r1,r2
           sw t30(r0),r3


           % processing: t31 := t25 + t30
           lw r1,t25(r0)
           lw r2,t30(r0)
           add r3,r1,r2
           sw t31(r0),r3


           % processing: t32 := t23 + t31
           lw r1,t23(r0)
           lw r2,t31(r0)
           add r3,r1,r2
           sw t32(r0),r3


           % processing: t33 := t21 + t32
           lw r1,t21(r0)
           lw r2,t32(r0)
           add r3,r1,r2
           sw t33(r0),r3


           % processing: t34 := t19 + t33
           lw r1,t19(r0)
           lw r2,t33(r0)
           add r3,r1,r2
           sw t34(r0),r3


           % processing: t35 := t17 + t34
           lw r1,t17(r0)
           lw r2,t34(r0)
           add r3,r1,r2
           sw t35(r0),r3


           %assigning values
           lw r9,t35(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t36(r0),r9


           % processing: t37 := x * t36
           lw r1,x(r0)
           lw r2,t36(r0)
           mul r3,r1,r2
           sw t37(r0),r3


           % processing: put(t37)
           lw r1,t37(r0)
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

           hlt
% space for variable buffer
buf     res 20
% space for variable x
x       res 4
% space for variable arr
arr     res 4
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
% space for variable t16
t16     res 4
% space for array value
t17        res 4
% space for variable t18
t18     res 4
% space for array value
t19        res 4
% space for variable t20
t20     res 4
% space for array value
t21        res 4
% space for variable t22
t22     res 4
% space for array value
t23        res 4
% space for variable t24
t24     res 4
% space for array value
t25        res 4
% space for variable t26
t26     res 4
% space for array value
t27        res 4
% space for variable t28
t28     res 4
% space for array value
t29        res 4
% space for t27 + t29
t30        res 4
% space for t25 + t30
t31        res 4
% space for t23 + t31
t32        res 4
% space for t21 + t32
t33        res 4
% space for t19 + t33
t34        res 4
% space for t17 + t34
t35        res 4
% space for variable t36
t36     res 4
% space for x * t36
t37        res 4
