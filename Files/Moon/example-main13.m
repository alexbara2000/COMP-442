
           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t1(r0),r9

% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t2(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t3(r0),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,0
           lw r9,t3(r0)
           sw x(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t4(r0),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,4
           lw r9,t4(r0)
           sw x(r8),r9


           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,0
           lw r1,x(r9)
           sw t5(r0),r1



           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,4
           lw r1,x(r9)
           sw t6(r0),r1



           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,4
           lw r1,x(r9)
           sw t7(r0),r1



           % processing: t8 = t6 * t7
           lw r1,t6(r0)
           lw r2,t7(r0)
           mul r3,r1,r2
           sw t8(r0),r3


           % processing: t9 = t5 + t8
           lw r1,t5(r0)
           lw r2,t8(r0)
           add r3,r1,r2
           sw t9(r0),r3


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,t9(r0)
           sw x(r8),r9


           %assigning values in factor for members
           sub r9,r9,r9
           addi r9,r9,8
           lw r1,x(r9)
           sw t10(r0),r1



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

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t11(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t12(r0),r9


           %assigning values to member
           lw r1,t11(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,8
           lw r9,t12(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t13(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t14(r0),r9


           %assigning values to member
           lw r1,t13(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,4
           lw r9,t14(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t15(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t16(r0),r9


           %assigning values in factor for members
           lw r3,t16(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t17(r0),r1



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t18(r0),r9


           %assigning values in factor for members
           lw r3,t18(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,4
           lw r1,y(r8)
           sw t19(r0),r1



           % processing: t20 = t17 + t19
           lw r1,t17(r0)
           lw r2,t19(r0)
           add r3,r1,r2
           sw t20(r0),r3


           %assigning values to member
           lw r1,t15(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r9,t20(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t21(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t22(r0),r9


           %assigning values in factor for members
           lw r3,t22(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t23(r0),r1



           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t24(r0),r9


           %assigning values in factor for members
           lw r3,t24(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t25(r0),r1



           % processing: t26 = t23 * t25
           lw r1,t23(r0)
           lw r2,t25(r0)
           mul r3,r1,r2
           sw t26(r0),r3


           %assigning values to member
           lw r1,t21(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,8
           lw r9,t26(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t27(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t28(r0),r9


           %assigning values in factor for members
           lw r3,t28(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t29(r0),r1



           %assigning values to member
           lw r1,t27(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r9,t29(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t30(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t31(r0),r9


           %assigning values in factor for members
           lw r3,t31(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t32(r0),r1



           %assigning values to member
           lw r1,t30(r0)
           muli r2,r1,16
           sub r8,r8,r8
           addi r8,r2,4
           lw r9,t32(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t33(r0),r9


           %assigning values in factor for members
           lw r3,t33(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t34(r0),r1



           % processing: put(t34)
           lw r1,t34(r0)
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
           addi r9,r9,2
           sw t35(r0),r9


           %assigning values in factor for members
           lw r3,t35(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t36(r0),r1



           % processing: put(t36)
           lw r1,t36(r0)
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
           addi r9,r9,3
           sw t37(r0),r9


           %assigning values in factor for members
           lw r3,t37(r0)
           muli r2,r3,16
           sub r8,r8,r8
           addi r8,r2,4
           lw r1,y(r8)
           sw t38(r0),r1



           % processing: put(t38)
           lw r1,t38(r0)
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
% space for variable t1
t1      res 4
% space for variable x
x       res 16
% space for variable y
y       res 80
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for array value
t5         res 4
% space for array value
t6         res 4
% space for array value
t7         res 4
% space for t6 * t7
t8         res 4
% space for t5 + t8
t9         res 4
% space for array value
t10        res 4
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
% space for t17 + t19
t20        res 4
% space for variable t21
t21     res 4
% space for variable t22
t22     res 4
% space for array value
t23        res 4
% space for variable t24
t24     res 4
% space for array value
t25        res 4
% space for t23 * t25
t26        res 4
% space for variable t27
t27     res 4
% space for variable t28
t28     res 4
% space for array value
t29        res 4
% space for variable t30
t30     res 4
% space for variable t31
t31     res 4
% space for array value
t32        res 4
% space for variable t33
t33     res 4
% space for array value
t34        res 4
% space for variable t35
t35     res 4
% space for array value
t36        res 4
% space for variable t37
t37     res 4
% space for array value
t38        res 4
