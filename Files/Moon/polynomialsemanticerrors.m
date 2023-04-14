% start of function
evaluate

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t1(r0),r9


           %putting return value of function
           lw r1,t1(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
build

           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,0
           lw r9,A(r0)
           sw new_function(r8),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,B(r0)
           sw new_function(r8),r9


           %putting return value of function
           lw r1,new_function(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
evaluate

           %assigning values
           lw r9,(r0)
           sw result(r0),r9


           % processing: t2 = a * x
           lw r1,a(r0)
           lw r2,x(r0)
           mul r3,r1,r2
           sw t2(r0),r3


           % processing: t3 = t2 + b
           lw r1,t2(r0)
           lw r2,b(r0)
           add r3,r1,r2
           sw t3(r0),r3


           %assigning values
           lw r9,t3(r0)
           sw result(r0),r9


           %putting return value of function
           lw r1,result(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
evaluate

           %assigning values
           lw r9,a(r0)
           sw result(r0),r9


           % processing: t4 = result * x
           lw r1,result(r0)
           lw r2,x(r0)
           mul r3,r1,r2
           sw t4(r0),r3


           % processing: t5 = t4 + b
           lw r1,t4(r0)
           lw r2,b(r0)
           add r3,r1,r2
           sw t5(r0),r3


           %assigning values
           lw r9,t5(r0)
           sw result(r0),r9


           % processing: t6 = result * x
           lw r1,result(r0)
           lw r2,x(r0)
           mul r3,r1,r2
           sw t6(r0),r3


           % processing: t7 = t6 + c
           lw r1,t6(r0)
           lw r2,c(r0)
           add r3,r1,r2
           sw t7(r0),r3


           %assigning values
           lw r9,t7(r0)
           sw result(r0),r9


           %putting return value of function
           lw r1,result(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
build

           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t8(r0),r9


           % processing: t9 = B * t8
           lw r1,B(r0)
           lw r2,t8(r0)
           mul r3,r1,r2
           sw t9(r0),r3


           % processing: t10 = A + t9
           lw r1,A(r0)
           lw r2,t9(r0)
           add r3,r1,r2
           sw t10(r0),r3


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,0
           lw r9,t10(r0)
           sw new_function(r8),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,B(r0)
           sw new_function(r8),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t11(r0),r9


           %putting return value of function
           lw r1,t11(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
build2

           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,0
           lw r9,A(r0)
           sw new_function(r8),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,B(r0)
           sw new_function(r8),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,16
           lw r9,C(r0)
           sw new_function(r8),r9


           %putting return value of function
           lw r1,new_function(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
f
           jr r12
% start of function
f
           jr r12
% start of function
f
           jr r12
% start of function
f3

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t12(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t13(r0),r9

           jr r12
% start of function
printArray

           %assigning values
           lw r9,size(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t14(r0),r9


           %assigning values
           lw r9,t14(r0)
           sw i(r0),r9


%checking while loop condition
gowhile1

           % processing: t15 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t15(r0),r3

           lw r1, t15(r0)
           bz r1, endRel1
statblock1

           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t16(r0),r9



           % processing: put(t16)
           lw r1,t16(r0)
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
           sw t17(r0),r9


           % processing: t18 = i + t17
           lw r1,i(r0)
           lw r2,t17(r0)
           add r3,r1,r2
           sw t18(r0),r3


           %assigning values
           lw r9,t18(r0)
           sw i(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel1
           jr r12
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t19(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t20(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t21(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t22(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t23(r0),r9


           %assigning values
           lw r9,counter(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t24(r0),r9


           % calling function: f1
           jl r12,f1

           % calling function: f2
           jl r12,f2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t25(r0),r9


           %assigning values to member
           sub r8,r8,r8
           addi r8,r8,8
           lw r9,t25(r0)
           sw c(r8),r9


           %assigning values
           lw r9,c(r0)
           sw counter(r0),r9


           % calling function: undefined
           jl r12,undefined

           %assigning values
           lw r9,a(r0)
           sw counter(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t26(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t27(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t28(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t29(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t30(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t26(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,t27(r4)
           sw size(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t31(r0),r9


           % calling function: f

           % defining params of function and propagating it for the size: i
           addi r4, r0, 0
           lw r1,(r4)
           sw i(r4),r1 

           jl r12,f

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t32(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t33(r0),r9


           %assigning values
           lw r1,t32(r0)
           muli r2,r1,4
           lw r9,t33(r0)
           sw i(r2),r9


           % calling function: f3

           % defining params of function and propagating it for the size: p1
           jl r12,f3

           % getting return value
           lw r1,fnres(r0)
           sw t34(r0),r1 


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t35(r0),r9


%checking while loop condition
gowhile2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t36(r0),r9


           % processing: t37 = counter <= t36
           lw r1,counter(r0)
           lw r2,t36(r0)
           cle r3,r1,r2
           sw t37(r0),r3

           lw r1, t37(r0)
           bz r1, endRel2
statblock2

           % processing: put(counter)
           lw r1,counter(r0)
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

           % processing: put(f1)
           lw r1,f1(r0)
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

           % processing: put(f2)
           lw r1,f2(r0)
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

%finished doing stat block going back to top
           j gowhile2

endRel2
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable t1
t1      res 4
% space for variable new_function
new_function res 32
% space for variable result
result  res 8
% space for a * x
t2         res 4
% space for t2 + b
t3         res 4
% space for variable result
result  res 8
% space for result * x
t4         res 4
% space for t4 + b
t5         res 4
% space for result * x
t6         res 4
% space for t6 + c
t7         res 4
% space for variable new_function
new_function res 40
% space for variable t8
t8      res 4
% space for B * t8
t9         res 4
% space for A + t9
t10        res 4
% space for variable t11
t11     res 4
% space for variable new_function
new_function res 40
% space for function parameter i
i       res 4
% space for function parameter i
i       res 4
% space for function parameter i
i       res 4
% space for function parameter j
j       res 4
% space for variable t12
t12     res 4
% space for variable t13
t13     res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable t14
t14     res 4
% space for i < n
t15        res 4
% space for array value
t16        res 4
% space for variable t17
t17     res 4
% space for i + t17
t18        res 4
% space for variable a
a       res 0
% space for variable c
c       res 4
% space for variable f1
f1      res 32
% space for variable f2
f2      res 40
% space for variable counter
counter res 4
% space for variable x
x       res 8
% space for variable i
i       res 24
% space for variable t19
t19     res 4
% space for variable t20
t20     res 4
% space for variable j
j       res 24
% space for variable t21
t21     res 4
% space for variable t22
t22     res 4
% space for variable t23
t23     res 4
% space for variable t24
t24     res 4
% space for variable t25
t25     res 4
% space for variable t26
t26     res 4
% space for variable t27
t27     res 4
% space for variable t28
t28     res 4
% space for variable t29
t29     res 4
% space for variable t30
t30     res 4
% space for variable t31
t31     res 4
% space for variable t32
t32     res 4
% space for variable t33
t33     res 4
% space for return value of function: f3
t34        res 4
% space for variable t35
t35     res 4
% space for variable t36
t36     res 4
% space for counter < t36
t37        res 4
