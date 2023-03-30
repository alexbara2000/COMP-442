% start of function
func

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t1(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t2(r0),r9


           %assigning values in factor
           lw r1,t2(r0)
           muli r2,r1,4
           lw r9,a(r2)
           sw t3(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t4(r0),r9


           %assigning values in factor
           lw r1,t4(r0)
           muli r2,r1,4
           lw r9,a(r2)
           sw t5(r0),r9



           % processing: t6 = t3 + t5
           lw r1,t3(r0)
           lw r2,t5(r0)
           add r3,r1,r2
           sw t6(r0),r3


           %assigning values
           lw r9,t6(r0)
           sw z(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t7(r0),r9


           %assigning values in factor
           lw r1,t7(r0)
           muli r2,r1,4
           lw r9,a(r2)
           sw t8(r0),r9



           % processing: put(t8)
           lw r1,t8(r0)
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


           %assigning values in factor
           lw r1,t9(r0)
           muli r2,r1,4
           lw r9,a(r2)
           sw t10(r0),r9



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

           %putting return value of function
           lw r1,z(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t11(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t12(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t13(r0),r9


           %assigning values
           lw r1,t12(r0)
           muli r2,r1,4
           lw r9,t13(r0)
           sw y(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t14(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t15(r0),r9


           %assigning values
           lw r1,t14(r0)
           muli r2,r1,4
           lw r9,t15(r0)
           sw y(r2),r9


           % calling function: func

           % defining params of function and propagating it for the size: a
           addi r4, r0, 0
           lw r1,y(r4)
           sw a(r4),r1 

           addi r4, r0, 4
           lw r1,y(r4)
           sw a(r4),r1 

           jl r12,func

           % getting return value
           lw r1,fnres(r0)
           sw t16(r0),r1 


           %assigning values
           lw r9,t16(r0)
           sw x(r0),r9


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
% space for function parameter a
a       res 8
% space for variable t1
t1      res 4
% space for variable z
z       res 4
% space for variable t2
t2      res 4
% space for array value
t3         res 4
% space for variable t4
t4      res 4
% space for array value
t5         res 4
% space for t3 + t5
t6         res 4
% space for variable t7
t7      res 4
% space for array value
t8         res 4
% space for variable t9
t9      res 4
% space for array value
t10        res 4
% space for variable x
x       res 4
% space for variable y
y       res 8
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
% space for return value of function: func
t16        res 4
