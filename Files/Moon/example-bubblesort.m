% start of function
bubbleSort

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t1(r0),r9


           %assigning values
           lw r9,size2(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t2(r0),r9


           %assigning values
           lw r9,t2(r0)
           sw i(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t3(r0),r9


           %assigning values
           lw r9,t3(r0)
           sw k(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t4(r0),r9


           %assigning values
           lw r9,t4(r0)
           sw temp(r0),r9


%checking while loop condition
gowhile1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t5(r0),r9


           % processing: t6 = n - t5
           lw r1,n(r0)
           lw r2,t5(r0)
           sub r3,r1,r2
           sw t6(r0),r3


           % processing: t7 = i < t6
           lw r1,i(r0)
           lw r2,t6(r0)
           clt r3,r1,r2
           sw t7(r0),r3

           lw r1, t7(r0)
           bz r1, endRel1
statblock1

%checking while loop condition
gowhile2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t8(r0),r9


           % processing: t9 = i + t8
           lw r1,i(r0)
           lw r2,t8(r0)
           add r3,r1,r2
           sw t9(r0),r3


           % processing: t10 = n - t9
           lw r1,n(r0)
           lw r2,t9(r0)
           sub r3,r1,r2
           sw t10(r0),r3


           % processing: t11 = k < t10
           lw r1,k(r0)
           lw r2,t10(r0)
           clt r3,r1,r2
           sw t11(r0),r3

           lw r1, t11(r0)
           bz r1, endRel2
statblock2

           %assigning values in factor
           lw r1,k(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t12(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t13(r0),r9


           % processing: t14 = k + t13
           lw r1,k(r0)
           lw r2,t13(r0)
           add r3,r1,r2
           sw t14(r0),r3


           %assigning values in factor
           lw r1,t14(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t15(r0),r9



           % processing: t16 = t12 > t15
           lw r1,t12(r0)
           lw r2,t15(r0)
           cgt r3,r1,r2
           sw t16(r0),r3


%checking if condition
           lw r1, t16(r0)
           bz r1, statblock4
statblock3

           %assigning values in factor
           lw r1,k(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t17(r0),r9



           %assigning values
           lw r9,t17(r0)
           sw temp(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t18(r0),r9


           % processing: t19 = k + t18
           lw r1,k(r0)
           lw r2,t18(r0)
           add r3,r1,r2
           sw t19(r0),r3


           %assigning values in factor
           lw r1,t19(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t20(r0),r9



           %assigning values
           lw r1,k(r0)
           muli r2,r1,4
           lw r9,t20(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t21(r0),r9


           % processing: t22 = k + t21
           lw r1,k(r0)
           lw r2,t21(r0)
           add r3,r1,r2
           sw t22(r0),r3


           %assigning values
           lw r1,t22(r0)
           muli r2,r1,4
           lw r9,temp(r0)
           sw arr(r2),r9


%finished doing stat block 1
           j endRel3
statblock4

endRel3

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t23(r0),r9


           % processing: t24 = k + t23
           lw r1,k(r0)
           lw r2,t23(r0)
           add r3,r1,r2
           sw t24(r0),r3


           %assigning values
           lw r9,t24(r0)
           sw k(r0),r9


%finished doing stat block going back to top
           j gowhile2

endRel2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t25(r0),r9


           % processing: t26 = i + t25
           lw r1,i(r0)
           lw r2,t25(r0)
           add r3,r1,r2
           sw t26(r0),r3


           %assigning values
           lw r9,t26(r0)
           sw i(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t27(r0),r9


           %assigning values
           lw r9,t27(r0)
           sw k(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel1
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t28(r0),r9


           %assigning values
           lw r9,size1(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t29(r0),r9


           %assigning values
           lw r9,t29(r0)
           sw y(r0),r9


%checking while loop condition
gowhile3

           % processing: t30 = y < x
           lw r1,y(r0)
           lw r2,x(r0)
           clt r3,r1,r2
           sw t30(r0),r3

           lw r1, t30(r0)
           bz r1, endRel4
statblock5

           %assigning values in factor
           lw r1,y(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t31(r0),r9



           % processing: put(t31)
           lw r1,t31(r0)
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
           sw t32(r0),r9


           % processing: t33 = y + t32
           lw r1,y(r0)
           lw r2,t32(r0)
           add r3,r1,r2
           sw t33(r0),r3


           %assigning values
           lw r9,t33(r0)
           sw y(r0),r9


%finished doing stat block going back to top
           j gowhile3

endRel4
           jr r12
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t34(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t35(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,64
           sw t36(r0),r9


           %assigning values
           lw r1,t35(r0)
           muli r2,r1,4
           lw r9,t36(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t37(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,34
           sw t38(r0),r9


           %assigning values
           lw r1,t37(r0)
           muli r2,r1,4
           lw r9,t38(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t39(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,25
           sw t40(r0),r9


           %assigning values
           lw r1,t39(r0)
           muli r2,r1,4
           lw r9,t40(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t41(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,12
           sw t42(r0),r9


           %assigning values
           lw r1,t41(r0)
           muli r2,r1,4
           lw r9,t42(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,4
           sw t43(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,22
           sw t44(r0),r9


           %assigning values
           lw r1,t43(r0)
           muli r2,r1,4
           lw r9,t44(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t45(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,11
           sw t46(r0),r9


           %assigning values
           lw r1,t45(r0)
           muli r2,r1,4
           lw r9,t46(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t47(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,90
           sw t48(r0),r9


           %assigning values
           lw r1,t47(r0)
           muli r2,r1,4
           lw r9,t48(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t49(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr

           % defining params of function and propagating it for the size: size1
           addi r4, r0, 0
           lw r1,t49(r4)
           sw size1(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t50(r0),r9


           % calling function: bubbleSort

           % defining params of function and propagating it for the size: arr

           % defining params of function and propagating it for the size: size2
           addi r4, r0, 0
           lw r1,t50(r4)
           sw size2(r4),r1 

           jl r12,bubbleSort

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10000
           sw t51(r0),r9


           % processing: put(t51)
           lw r1,t51(r0)
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
           addi r9,r9,7
           sw t52(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr

           % defining params of function and propagating it for the size: size1
           addi r4, r0, 0
           lw r1,t52(r4)
           sw size1(r4),r1 

           jl r12,printArray
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for function parameter size2
size2   res 4
% space for variable t1
t1      res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable k
k       res 4
% space for variable temp
temp    res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for variable t5
t5      res 4
% space for n - t5
t6         res 4
% space for i < t6
t7         res 4
% space for variable t8
t8      res 4
% space for i + t8
t9         res 4
% space for n - t9
t10        res 4
% space for k < t10
t11        res 4
% space for array value
t12        res 4
% space for variable t13
t13     res 4
% space for k + t13
t14        res 4
% space for array value
t15        res 4
% space for t12 < t15
t16        res 4
% space for array value
t17        res 4
% space for variable t18
t18     res 4
% space for k + t18
t19        res 4
% space for array value
t20        res 4
% space for variable t21
t21     res 4
% space for k + t21
t22        res 4
% space for variable t23
t23     res 4
% space for k + t23
t24        res 4
% space for variable t25
t25     res 4
% space for i + t25
t26        res 4
% space for variable t27
t27     res 4
% space for function parameter size1
size1   res 4
% space for variable t28
t28     res 4
% space for variable x
x       res 4
% space for variable y
y       res 4
% space for variable t29
t29     res 4
% space for y < x
t30        res 4
% space for array value
t31        res 4
% space for variable t32
t32     res 4
% space for y + t32
t33        res 4
% space for variable arr
arr     res 28
% space for variable t34
t34     res 4
% space for variable t35
t35     res 4
% space for variable t36
t36     res 4
% space for variable t37
t37     res 4
% space for variable t38
t38     res 4
% space for variable t39
t39     res 4
% space for variable t40
t40     res 4
% space for variable t41
t41     res 4
% space for variable t42
t42     res 4
% space for variable t43
t43     res 4
% space for variable t44
t44     res 4
% space for variable t45
t45     res 4
% space for variable t46
t46     res 4
% space for variable t47
t47     res 4
% space for variable t48
t48     res 4
% space for variable t49
t49     res 4
% space for variable t50
t50     res 4
% space for variable t51
t51     res 4
% space for variable t52
t52     res 4
