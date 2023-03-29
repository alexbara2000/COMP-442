% start of function
func

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t1(r0),r9


           %putting return value of function
           lw r1,t1(r0)
           sw fnres(r0), r1
           jr r15
           jr r15
% start
           entry
           addi   r14,r0,topaddr  % Set stack pointer

           % calling function: func
           jl r15,func

           % getting return value
           lw r1,fnres(r0)
           sw t2(r0),r1 



           %assigning values
           lw r9,t2(r0)
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
% space for variable t1
t1      res 4
% space for variable x
x       res 4
% space for return value of function: func
t2         res 4
