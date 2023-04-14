% start of function
func

           %assigning values
           sub r9,r9,r9
           addi r9,r9,132
           sw t1(r0),r9


           %assigning values
           lw r9,t1(r0)
           sw x(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,12
           sw t2(r0),r9


           % processing: t3 = x / t2
           lw r1,x(r0)
           lw r2,t2(r0)
           div r3,r1,r2
           sw t3(r0),r3


           %assigning values
           lw r9,t3(r0)
           sw y(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t4(r0),r9


           %assigning values
           lw r9,t4(r0)
           sw z(r0),r9


           %assigning values
           lw r9,z(r0)
           sw q(r0),r9


           % processing: t5 = z == z
           lw r1,z(r0)
           lw r2,z(r0)
           ceq r3,r1,r2
           sw t5(r0),r3


%checking if condition
           lw r1, t5(r0)
           bz r1, statblock4
statblock1

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

           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t6(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t7(r0),r9


           % processing: t8 = t6 == t7
           lw r1,t6(r0)
           lw r2,t7(r0)
           ceq r3,r1,r2
           sw t8(r0),r3


%checking if condition
           lw r1, t8(r0)
           bz r1, statblock3
statblock2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10000
           sw t9(r0),r9


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

%finished doing stat block 1
           j endRel2
statblock3

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
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

endRel2

%finished doing stat block 1
           j endRel1
statblock4

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

endRel1

           % processing: put(q)
           lw r1,q(r0)
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
           jr r12
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           % calling function: func
           jl r12,func
           hlt

% space for variable buffer
buf     res 20
fnres   res 4
% space for variable x
x       res 4
% space for variable y
y       res 4
% space for variable z
z       res 4
% space for variable q
q       res 4
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for x / t2
t3         res 4
% space for variable t4
t4      res 4
% space for z == z
t5         res 4
% space for variable t6
t6      res 4
% space for variable t7
t7      res 4
% space for t6 == t7
t8         res 4
% space for variable t9
t9      res 4
% space for variable t10
t10     res 4
