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

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t10(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t11(r0),r9


           %assigning values to member
           lw r1,t10(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,8
           lw r9,t11(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t12(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t13(r0),r9


           %assigning values to member
           lw r1,t12(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,4
           lw r9,t13(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t14(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t15(r0),r9


           %assigning values in factor for members
           lw r3,t15(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t16(r0),r1



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t17(r0),r9


           %assigning values in factor for members
           lw r3,t17(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,4
           lw r1,y(r8)
           sw t18(r0),r1



           % processing: t19 = t16 + t18
           lw r1,t16(r0)
           lw r2,t18(r0)
           add r3,r1,r2
           sw t19(r0),r3


           %assigning values to member
           lw r1,t14(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r9,t19(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t20(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t21(r0),r9


           %assigning values in factor for members
           lw r3,t21(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t22(r0),r1



           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t23(r0),r9


           %assigning values in factor for members
           lw r3,t23(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t24(r0),r1



           % processing: t25 = t22 * t24
           lw r1,t22(r0)
           lw r2,t24(r0)
           mul r3,r1,r2
           sw t25(r0),r3


           %assigning values to member
           lw r1,t20(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,8
           lw r9,t25(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t26(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t27(r0),r9


           %assigning values in factor for members
           lw r3,t27(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t28(r0),r1



           %assigning values to member
           lw r1,t26(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r9,t28(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t29(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t30(r0),r9


           %assigning values in factor for members
           lw r3,t30(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t31(r0),r1



           %assigning values to member
           lw r1,t29(r0)
           muli r2,r1,12
           sub r8,r8,r8
           addi r8,r2,4
           lw r9,t31(r0)
           sw y(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t32(r0),r9


           %assigning values in factor for members
           lw r3,t32(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,8
           lw r1,y(r8)
           sw t33(r0),r1



           % processing: put(t33)
           lw r1,t33(r0)
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
           sw t34(r0),r9


           %assigning values in factor for members
           lw r3,t34(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,0
           lw r1,y(r8)
           sw t35(r0),r1



           % processing: put(t35)
           lw r1,t35(r0)
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
           sw t36(r0),r9


           %assigning values in factor for members
           lw r3,t36(r0)
           muli r2,r3,12
           sub r8,r8,r8
           addi r8,r2,4
           lw r1,y(r8)
           sw t37(r0),r1



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
% space for array value
t16        res 4
% space for variable t17
t17     res 4
% space for array value
t18        res 4
% space for t16 + t18
t19        res 4
% space for variable t20
t20     res 4
% space for variable t21
t21     res 4
% space for array value
t22        res 4
% space for variable t23
t23     res 4
% space for array value
t24        res 4
% space for t22 * t24
t25        res 4
% space for variable t26
t26     res 4
% space for variable t27
t27     res 4
% space for array value
t28        res 4
% space for variable t29
t29     res 4
% space for variable t30
t30     res 4
% space for array value
t31        res 4
% space for variable t32
t32     res 4
% space for array value
t33        res 4
% space for variable t34
t34     res 4
% space for array value
t35        res 4
% space for variable t36
t36     res 4
% space for array value
t37        res 4
