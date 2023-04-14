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
evaluate

           %assigning values
           lw r9,a(r0)
           sw result(r0),r9


           % processing: t2 = result * x
           lw r1,result(r0)
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


           % processing: t4 = result * x
           lw r1,result(r0)
           lw r2,x(r0)
           mul r3,r1,r2
           sw t4(r0),r3


           % processing: t5 = t4 + c
           lw r1,t4(r0)
           lw r2,c(r0)
           add r3,r1,r2
           sw t5(r0),r3


           %assigning values
           lw r9,t5(r0)
           sw result(r0),r9


           %putting return value of function
           lw r1,result(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
build

           %assigning values
           lw r9,A(r0)
           sw a(r0),r9


           %assigning values
           lw r9,B(r0)
           sw b(r0),r9


           %assigning values
           lw r9,C(r0)
           sw c(r0),r9

           jr r12
% start of function
build

           %assigning values
           lw r9,A(r0)
           sw a(r0),r9


           %assigning values
           lw r9,B(r0)
           sw b(r0),r9

           jr r12
% start of function
evaluate

           %assigning values
           lw r9,(r0)
           sw result(r0),r9


           % processing: t6 = a * x
           lw r1,a(r0)
           lw r2,x(r0)
           mul r3,r1,r2
           sw t6(r0),r3


           % processing: t7 = t6 + b
           lw r1,t6(r0)
           lw r2,b(r0)
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
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t8(r0),r9


%checking while loop condition
gowhile1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t9(r0),r9


           % processing: t10 = counter <= t9
           lw r1,counter(r0)
           lw r2,t9(r0)
           cle r3,r1,r2
           sw t10(r0),r3

           lw r1, t10(r0)
           bz r1, endRel1
statblock1

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
           j gowhile1

endRel1
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable t1
t1      res 4
% space for variable result
result  res 8
% space for result * x
t2         res 4
% space for t2 + b
t3         res 4
% space for result * x
t4         res 4
% space for t4 + c
t5         res 4
% space for variable result
result  res 8
% space for a * x
t6         res 4
% space for t6 + b
t7         res 4
% space for variable f1
f1      res 32
% space for variable t8
t8      res 4
% space for variable f2
f2      res 40
% space for variable counter
counter res 4
% space for variable t9
t9      res 4
% space for counter < t9
t10        res 4
