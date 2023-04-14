% start of function
func

           % processing: t1 = a + b
           lw r1,a(r0)
           lw r2,b(r0)
           add r3,r1,r2
           sw t1(r0),r3


           %assigning values
           lw r9,t1(r0)
           sw y(r0),r9


           %putting return value of function
           lw r1,y(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
func2

           % processing: t2 = c - d
           lw r1,c(r0)
           lw r2,d(r0)
           sub r3,r1,r2
           sw t2(r0),r3


           %assigning values
           lw r9,t2(r0)
           sw z(r0),r9


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
           sw t3(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,3
           sw t4(r0),r9


           % calling function: func

           % defining params of function and propagating it for the size: a
           addi r4, r0, 0
           lw r1,t3(r4)
           sw a(r4),r1 


           % defining params of function and propagating it for the size: b
           addi r4, r0, 0
           lw r1,t4(r4)
           sw b(r4),r1 

           jl r12,func

           % getting return value
           lw r1,fnres(r0)
           sw t5(r0),r1 


           %assigning values
           lw r9,t5(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t6(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t7(r0),r9


           % processing: t8 = t6 * t7
           lw r1,t6(r0)
           lw r2,t7(r0)
           mul r3,r1,r2
           sw t8(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,4
           sw t9(r0),r9


           % calling function: func2

           % defining params of function and propagating it for the size: c
           addi r4, r0, 0
           lw r1,t8(r4)
           sw c(r4),r1 


           % defining params of function and propagating it for the size: d
           addi r4, r0, 0
           lw r1,t9(r4)
           sw d(r4),r1 

           jl r12,func2

           % getting return value
           lw r1,fnres(r0)
           sw t10(r0),r1 


           % processing: t11 = x + t10
           lw r1,x(r0)
           lw r2,t10(r0)
           add r3,r1,r2
           sw t11(r0),r3


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
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for function parameter a
a       res 4
% space for function parameter b
b       res 4
% space for variable y
y       res 4
% space for a + b
t1         res 4
% space for function parameter c
c       res 4
% space for function parameter d
d       res 4
% space for variable z
z       res 4
% space for c - d
t2         res 4
% space for variable x
x       res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for return value of function: func
t5         res 4
% space for variable t6
t6      res 4
% space for variable t7
t7      res 4
% space for t6 * t7
t8         res 4
% space for variable t9
t9      res 4
% space for return value of function: func2
t10        res 4
% space for x + t10
t11        res 4
