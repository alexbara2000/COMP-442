% start of function
bubbleSort

           %assigning values
           lw r9,size(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t1(r0),r9


           %assigning values
           lw r9,t1(r0)
           sw i(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t2(r0),r9


           %assigning values
           lw r9,t2(r0)
           sw j(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t3(r0),r9


           %assigning values
           lw r9,t3(r0)
           sw temp(r0),r9


%checking while loop condition
gowhile1

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t4(r0),r9


           % processing: t5 = n - t4
           lw r1,n(r0)
           lw r2,t4(r0)
           sub r3,r1,r2
           sw t5(r0),r3


           % processing: t6 = i < t5
           lw r1,i(r0)
           lw r2,t5(r0)
           clt r3,r1,r2
           sw t6(r0),r3

           lw r1, t6(r0)
           bz r1, endRel1
statblock1

%checking while loop condition
gowhile2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t7(r0),r9


           % processing: t8 = i - t7
           lw r1,i(r0)
           lw r2,t7(r0)
           sub r3,r1,r2
           sw t8(r0),r3


           % processing: t9 = n - t8
           lw r1,n(r0)
           lw r2,t8(r0)
           sub r3,r1,r2
           sw t9(r0),r3


           % processing: t10 = j < t9
           lw r1,j(r0)
           lw r2,t9(r0)
           clt r3,r1,r2
           sw t10(r0),r3

           lw r1, t10(r0)
           bz r1, endRel2
statblock2

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t11(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t12(r0),r9


           % processing: t13 = j + t12
           lw r1,j(r0)
           lw r2,t12(r0)
           add r3,r1,r2
           sw t13(r0),r3


           %assigning values in factor
           lw r1,t13(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t14(r0),r9



           % processing: t15 = t11 > t14
           lw r1,t11(r0)
           lw r2,t14(r0)
           cgt r3,r1,r2
           sw t15(r0),r3


%checking if condition
           lw r1, t15(r0)
           bz r1, statblock4
statblock3

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t16(r0),r9



           %assigning values
           lw r9,t16(r0)
           sw temp(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t17(r0),r9


           % processing: t18 = j + t17
           lw r1,j(r0)
           lw r2,t17(r0)
           add r3,r1,r2
           sw t18(r0),r3


           %assigning values in factor
           lw r1,t18(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t19(r0),r9



           %assigning values
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,t19(r0)
           sw arr(r2),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t20(r0),r9


           % processing: t21 = j + t20
           lw r1,j(r0)
           lw r2,t20(r0)
           add r3,r1,r2
           sw t21(r0),r3


           %assigning values
           lw r1,t21(r0)
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
           sw t22(r0),r9


           % processing: t23 = j + t22
           lw r1,j(r0)
           lw r2,t22(r0)
           add r3,r1,r2
           sw t23(r0),r3


           %assigning values
           lw r9,t23(r0)
           sw j(r0),r9


%finished doing stat block going back to top
           j gowhile2

endRel2

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t24(r0),r9


           % processing: t25 = i + t24
           lw r1,i(r0)
           lw r2,t24(r0)
           add r3,r1,r2
           sw t25(r0),r3


           %assigning values
           lw r9,t25(r0)
           sw i(r0),r9


%finished doing stat block going back to top
           j gowhile1

endRel1
           jr r12
% start of function
printArray

           %assigning values
           lw r9,size(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t26(r0),r9


           %assigning values
           lw r9,t26(r0)
           sw i(r0),r9


%checking while loop condition
gowhile3

           % processing: t27 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t27(r0),r3

           lw r1, t27(r0)
           bz r1, endRel4
statblock5

           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t28(r0),r9



           % processing: put(t28)
           lw r1,t28(r0)
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
           sw t29(r0),r9


           % processing: t30 = i + t29
           lw r1,i(r0)
           lw r2,t29(r0)
           add r3,r1,r2
           sw t30(r0),r3


           %assigning values
           lw r9,t30(r0)
           sw i(r0),r9


%finished doing stat block going back to top
           j gowhile3

endRel4
           jr r12
% start of function
functionBody

           %assigning values
           lw r9,size(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t31(r0),r9


           %assigning values
           lw r9,t31(r0)
           sw i(r0),r9


%checking while loop condition
gowhile4

           % processing: t32 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t32(r0),r3

           lw r1, t32(r0)
           bz r1, endRel5
statblock6

           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t33(r0),r9



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
           addi r9,r9,1
           sw t34(r0),r9


           % processing: t35 = i + t34
           lw r1,i(r0)
           lw r2,t34(r0)
           add r3,r1,r2
           sw t35(r0),r3


           %assigning values
           lw r9,t35(r0)
           sw i(r0),r9


%finished doing stat block going back to top
           j gowhile4

endRel5

%checking while loop condition
gowhile5

           % processing: t36 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t36(r0),r3

           lw r1, t36(r0)
           bz r1, endRel6
statblock7

           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t37(r0),r9



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

%finished doing stat block going back to top
           j gowhile5

endRel6

%checking while loop condition
gowhile6

           % processing: t38 = i < n
           lw r1,i(r0)
           lw r2,n(r0)
           clt r3,r1,r2
           sw t38(r0),r3

           lw r1, t38(r0)
           bz r1, endRel7
statblock8

%finished doing stat block going back to top
           j gowhile6

endRel7

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t39(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t40(r0),r9


           % processing: t41 = j + t40
           lw r1,j(r0)
           lw r2,t40(r0)
           add r3,r1,r2
           sw t41(r0),r3


           %assigning values in factor
           lw r1,t41(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t42(r0),r9



           % processing: t43 = t39 > t42
           lw r1,t39(r0)
           lw r2,t42(r0)
           cgt r3,r1,r2
           sw t43(r0),r3


%checking if condition
           lw r1, t43(r0)
           bz r1, statblock10
statblock9

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t44(r0),r9



           %assigning values
           lw r9,t44(r0)
           sw temp(r0),r9


%finished doing stat block 1
           j endRel8
statblock10

endRel8

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t45(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t46(r0),r9


           % processing: t47 = j + t46
           lw r1,j(r0)
           lw r2,t46(r0)
           add r3,r1,r2
           sw t47(r0),r3


           %assigning values in factor
           lw r1,t47(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t48(r0),r9



           % processing: t49 = t45 > t48
           lw r1,t45(r0)
           lw r2,t48(r0)
           cgt r3,r1,r2
           sw t49(r0),r3


%checking if condition
           lw r1, t49(r0)
           bz r1, statblock12
statblock11

%finished doing stat block 1
           j endRel9
statblock12

           %assigning values in factor
           lw r1,j(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t50(r0),r9



           %assigning values
           lw r9,t50(r0)
           sw temp(r0),r9


endRel9
           jr r12
% start of function
variableIdNest

           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t51(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t52(r0),r9


           % processing: t53 = t51 * t52
           lw r1,t51(r0)
           lw r2,t52(r0)
           mul r3,r1,r2
           sw t53(r0),r3


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t54(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t55(r0),r9


           % processing: t56 = t54 / t55
           lw r1,t54(r0)
           lw r2,t55(r0)
           div r3,r1,r2
           sw t56(r0),r3


           %assigning values
           lw r9,n(r0)
           sw n(r0),r9

           jr r12
% start of function
expressionsTest

           %assigning values
           lw r9,i(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t57(r0),r9


           % processing: t58 = n + t57
           lw r1,n(r0)
           lw r2,t57(r0)
           add r3,r1,r2
           sw t58(r0),r3


           %assigning values
           lw r9,t58(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t59(r0),r9


           % processing: t60 = n - t59
           lw r1,n(r0)
           lw r2,t59(r0)
           sub r3,r1,r2
           sw t60(r0),r3


           %assigning values
           lw r9,t60(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t61(r0),r9


           % processing: t62 = n * t61
           lw r1,n(r0)
           lw r2,t61(r0)
           mul r3,r1,r2
           sw t62(r0),r3


           %assigning values
           lw r9,t62(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t63(r0),r9


           % processing: t64 = n / t63
           lw r1,n(r0)
           lw r2,t63(r0)
           div r3,r1,r2
           sw t64(r0),r3


           %assigning values
           lw r9,t64(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t65(r0),r9


           % processing: t66 = n > t65
           lw r1,n(r0)
           lw r2,t65(r0)
           cgt r3,r1,r2
           sw t66(r0),r3


           %assigning values
           lw r9,t66(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t67(r0),r9


           % processing: t68 = n < t67
           lw r1,n(r0)
           lw r2,t67(r0)
           clt r3,r1,r2
           sw t68(r0),r3


           %assigning values
           lw r9,t68(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t69(r0),r9


           % processing: t70 = n > t69
           lw r1,n(r0)
           lw r2,t69(r0)
           cge r3,r1,r2
           sw t70(r0),r3


           %assigning values
           lw r9,t70(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t71(r0),r9


           % processing: t72 = n <= t71
           lw r1,n(r0)
           lw r2,t71(r0)
           cle r3,r1,r2
           sw t72(r0),r3


           %assigning values
           lw r9,t72(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t73(r0),r9


           %assigning values
           lw r9,(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t75(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t76(r0),r9


           % processing: t77 = t75 * t76
           lw r1,t75(r0)
           lw r2,t76(r0)
           mul r3,r1,r2
           sw t77(r0),r3


           % processing: t78 = n + t77
           lw r1,n(r0)
           lw r2,t77(r0)
           add r3,r1,r2
           sw t78(r0),r3


           %assigning values
           lw r9,t78(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t79(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t80(r0),r9


           % processing: t81 = t79 * t80
           lw r1,t79(r0)
           lw r2,t80(r0)
           mul r3,r1,r2
           sw t81(r0),r3


           % processing: t82 = n - t81
           lw r1,n(r0)
           lw r2,t81(r0)
           sub r3,r1,r2
           sw t82(r0),r3


           %assigning values
           lw r9,t82(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t83(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t84(r0),r9


           % processing: t85 = t83 / t84
           lw r1,t83(r0)
           lw r2,t84(r0)
           div r3,r1,r2
           sw t85(r0),r3


           % processing: t86 = n * t85
           lw r1,n(r0)
           lw r2,t85(r0)
           mul r3,r1,r2
           sw t86(r0),r3


           %assigning values
           lw r9,t86(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t87(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,44
           sw t88(r0),r9


           % processing: t89 = t87 / t88
           lw r1,t87(r0)
           lw r2,t88(r0)
           div r3,r1,r2
           sw t89(r0),r3


           % processing: t90 = n * t89
           lw r1,n(r0)
           lw r2,t89(r0)
           mul r3,r1,r2
           sw t90(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t91(r0),r9


           % processing: t92 = t90 > t91
           lw r1,t90(r0)
           lw r2,t91(r0)
           cgt r3,r1,r2
           sw t92(r0),r3


           %assigning values
           lw r9,t92(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,6
           sw t93(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,44
           sw t94(r0),r9


           % processing: t95 = t93 / t94
           lw r1,t93(r0)
           lw r2,t94(r0)
           div r3,r1,r2
           sw t95(r0),r3


           % processing: t96 = n * t95
           lw r1,n(r0)
           lw r2,t95(r0)
           mul r3,r1,r2
           sw t96(r0),r3


           % processing: t97 = t96 > i
           lw r1,t96(r0)
           lw r2,i(r0)
           cgt r3,r1,r2
           sw t97(r0),r3


           %assigning values
           lw r9,t97(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t98(r0),r9


           % processing: t99 = n + t98
           lw r1,n(r0)
           lw r2,t98(r0)
           add r3,r1,r2
           sw t99(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,8
           sw t100(r0),r9


           % processing: t101 = t99 * t100
           lw r1,t99(r0)
           lw r2,t100(r0)
           mul r3,r1,r2
           sw t101(r0),r3


           %assigning values
           lw r9,t101(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,8
           sw t102(r0),r9


           % processing: t103 = t102 * i
           lw r1,t102(r0)
           lw r2,i(r0)
           mul r3,r1,r2
           sw t103(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t104(r0),r9


           % processing: t105 = t103 / t104
           lw r1,t103(r0)
           lw r2,t104(r0)
           div r3,r1,r2
           sw t105(r0),r3


           % processing: t106 = n + t105
           lw r1,n(r0)
           lw r2,t105(r0)
           add r3,r1,r2
           sw t106(r0),r3


           %assigning values
           lw r9,t106(r0)
           sw n(r0),r9


           %assigning values
           lw r9,(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,19
           sw t107(r0),r9


           % processing: t108 =  + 
           lw r1,(r0)
           lw r2,(r0)
           add r3,r1,r2
           sw t108(r0),r3


           %assigning values
           lw r9,t108(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t109(r0),r9


           % processing: t110 = t109 - 
           lw r1,t109(r0)
           lw r2,(r0)
           sub r3,r1,r2
           sw t110(r0),r3


           %assigning values
           lw r9,t110(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t111(r0),r9


           % processing: t112 = t111 - 
           lw r1,t111(r0)
           lw r2,(r0)
           sub r3,r1,r2
           sw t112(r0),r3


           %assigning values
           lw r9,t112(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,17
           sw t113(r0),r9


           % processing: t114 = t113 * 
           lw r1,t113(r0)
           lw r2,(r0)
           mul r3,r1,r2
           sw t114(r0),r3


           %assigning values
           lw r9,t114(r0)
           sw n(r0),r9


           % processing: t115 =  * 
           lw r1,(r0)
           lw r2,(r0)
           mul r3,r1,r2
           sw t115(r0),r3


           % processing: t116 = t115 + i
           lw r1,t115(r0)
           lw r2,i(r0)
           add r3,r1,r2
           sw t116(r0),r3


           %assigning values
           lw r9,t116(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,8
           sw t117(r0),r9


           % processing: t118 = not t117
           lw r1,t117(r0)
           bnz r1,zero1
           addi r2,r0,1
           sw t118(r0),r2 
           j endRel10

zero1     sw t118(r0),r0 
endRel10  

           % processing: t119 = t118 > i
           lw r1,t118(r0)
           lw r2,i(r0)
           cgt r3,r1,r2
           sw t119(r0),r3


           %assigning values
           lw r9,t119(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t120(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t121(r0),r9


           % processing: t122 = t121 and i
           lw r1,t121(r0)
           lw r2,i(r0)
           bz r1,zero2
           bz r2,zero2
           addi r3,r0,1
           j endrel11

zero2      addi r3,r0,0
endrel11  sw t122(r0),r3



           % processing: t123 = t120 > t122
           lw r1,t120(r0)
           lw r2,t122(r0)
           cgt r3,r1,r2
           sw t123(r0),r3


           %assigning values
           lw r9,t123(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,7
           sw t124(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t125(r0),r9


           % processing: t126 = t125 and i
           lw r1,t125(r0)
           lw r2,i(r0)
           bnz r1,zero3
           bnz r2,zero3
           add r3,r0,r0
           j endRel12

zero3      addi r3,r0,1
endRel12  sw t126(r0),r3



           % processing: t127 = t124 > t126
           lw r1,t124(r0)
           lw r2,t126(r0)
           cgt r3,r1,r2
           sw t127(r0),r3


           %assigning values
           lw r9,t127(r0)
           sw n(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t128(r0),r9


           %assigning values in factor
           lw r1,t128(r0)
           muli r2,r1,4
           lw r9,n(r2)
           sw t129(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,9
           sw t130(r0),r9


           % processing: t131 = t129 * t130
           lw r1,t129(r0)
           lw r2,t130(r0)
           mul r3,r1,r2
           sw t131(r0),r3


           %assigning values
           sub r9,r9,r9
           addi r9,r9,2
           sw t132(r0),r9


           % processing: t133 = i * t132
           lw r1,i(r0)
           lw r2,t132(r0)
           mul r3,r1,r2
           sw t133(r0),r3


           %assigning values in factor
           lw r1,t133(r0)
           muli r2,r1,4
           lw r9,n(r2)
           sw t134(r0),r9



           % processing: t135 = t131 + t134
           lw r1,t131(r0)
           lw r2,t134(r0)
           add r3,r1,r2
           sw t135(r0),r3


           %assigning values
           lw r9,t135(r0)
           sw n(r0),r9

           jr r12
% start of function
functionBody
           jr r12
% start of function
functionBody

           %reading variable from the keyboard
           addi r1, r0, buf
           sw -8(r14),r1
           jl r3,getstr
           jl r12,strint
           sw n(r0),r12 


           % processing: put(n)
           lw r1,n(r0)
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

           % processing: t136 = n + 
           lw r1,n(r0)
           lw r2,(r0)
           add r3,r1,r2
           sw t136(r0),r3


           %putting return value of function
           lw r1,t136(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
functionCalls

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t137(r0),r9


           %assigning values
           lw r9,t137(r0)
           sw i(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,x(r4)
           sw arr(r4),r1 


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,x(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,y(r4)
           sw size(r4),r1 

           jl r12,printArray

           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,x(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,y(r4)
           sw size(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t138(r0),r9


           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t139(r0),r9



           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t138(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,(r4)
           sw size(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t140(r0),r9


           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t141(r0),r9



           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t142(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t140(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,(r4)
           sw size(r4),r1 

           jl r12,printArray

           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,x(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,y(r4)
           sw size(r4),r1 

           jl r12,printArray

           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,a(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,printArray(r4)
           sw size(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,77
           sw t143(r0),r9


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t143(r4)
           sw arr(r4),r1 


           %assigning values
           lw r9,printArray(r0)
           sw i(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t144(r0),r9


           % processing: t145 = i + t144
           lw r1,i(r0)
           lw r2,t144(r0)
           add r3,r1,r2
           sw t145(r0),r3


           %assigning values in factor
           lw r1,i(r0)
           muli r2,r1,4
           lw r9,arr(r2)
           sw t146(r0),r9



           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t145(r4)
           sw arr(r4),r1 


           % defining params of function and propagating it for the size: size
           addi r4, r0, 0
           lw r1,t146(r4)
           sw size(r4),r1 

           jl r12,printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,1
           sw t147(r0),r9


           % processing: t148 = i + t147
           lw r1,i(r0)
           lw r2,t147(r0)
           add r3,r1,r2
           sw t148(r0),r3


           % calling function: printArray

           % defining params of function and propagating it for the size: arr
           addi r4, r0, 0
           lw r1,t148(r4)
           sw arr(r4),r1 


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
functionParams

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t149(r0),r9

           jr r12
% start of function
variableDecls
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t150(r0),r9


           %assigning values
           lw r9,t150(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t151(r0),r9


           %assigning values
           lw r9,t151(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t152(r0),r9


           %assigning values
           lw r9,t152(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t153(r0),r9


           %assigning values
           lw r9,t153(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,5
           sw t154(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t155(r0),r9


           %assigning values
           lw r9,t155(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t156(r0),r9


           %assigning values
           lw r9,t156(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12
% start of function
printArray

           %assigning values
           sub r9,r9,r9
           addi r9,r9,10
           sw t157(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,111
           sw t158(r0),r9


           %assigning values
           sub r9,r9,r9
           addi r9,r9,0
           sw t159(r0),r9


           %assigning values
           lw r9,t159(r0)
           sw i(r0),r9


           %putting return value of function
           lw r1,i(r0)
           sw fnres(r0), r1
           jr r12
           jr r12

% space for variable buffer
buf     res 20
fnres   res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable i
i       res 4
% space for variable n
n       res 4
% space for variable j
j       res 4
% space for variable temp
temp    res 4
% space for variable testString
testString res 0
% space for variable t1
t1      res 4
% space for variable t2
t2      res 4
% space for variable t3
t3      res 4
% space for variable t4
t4      res 4
% space for n - t4
t5         res 4
% space for i < t5
t6         res 4
% space for variable t7
t7      res 4
% space for i - t7
t8         res 4
% space for n - t8
t9         res 4
% space for j < t9
t10        res 4
% space for array value
t11        res 4
% space for variable t12
t12     res 4
% space for j + t12
t13        res 4
% space for array value
t14        res 4
% space for t11 < t14
t15        res 4
% space for array value
t16        res 4
% space for variable t17
t17     res 4
% space for j + t17
t18        res 4
% space for array value
t19        res 4
% space for variable t20
t20     res 4
% space for j + t20
t21        res 4
% space for variable t22
t22     res 4
% space for j + t22
t23        res 4
% space for variable t24
t24     res 4
% space for i + t24
t25        res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable t26
t26     res 4
% space for i < n
t27        res 4
% space for array value
t28        res 4
% space for variable t29
t29     res 4
% space for i + t29
t30        res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable t31
t31     res 4
% space for i < n
t32        res 4
% space for array value
t33        res 4
% space for variable t34
t34     res 4
% space for i + t34
t35        res 4
% space for i < n
t36        res 4
% space for array value
t37        res 4
% space for i < n
t38        res 4
% space for array value
t39        res 4
% space for variable t40
t40     res 4
% space for j + t40
t41        res 4
% space for array value
t42        res 4
% space for t39 < t42
t43        res 4
% space for array value
t44        res 4
% space for array value
t45        res 4
% space for variable t46
t46     res 4
% space for j + t46
t47        res 4
% space for array value
t48        res 4
% space for t45 < t48
t49        res 4
% space for array value
t50        res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for variable t51
t51     res 4
% space for variable t52
t52     res 4
% space for t51 * t52
t53        res 4
% space for variable t54
t54     res 4
% space for variable t55
t55     res 4
% space for t54 / t55
t56        res 4
% space for variable n
n       res 4
% space for variable i
i       res 4
% space for variable t57
t57     res 4
% space for n + t57
t58        res 4
% space for variable t59
t59     res 4
% space for n - t59
t60        res 4
% space for variable t61
t61     res 4
% space for n * t61
t62        res 4
% space for variable t63
t63     res 4
% space for n / t63
t64        res 4
% space for variable t65
t65     res 4
% space for n < t65
t66        res 4
% space for variable t67
t67     res 4
% space for n < t67
t68        res 4
% space for variable t69
t69     res 4
% space for n < t69
t70        res 4
% space for variable t71
t71     res 4
% space for n < t71
t72        res 4
% space for variable t73
t73     res 4
% space for variable t75
t75     res 4
% space for variable t76
t76     res 4
% space for t75 * t76
t77        res 4
% space for n + t77
t78        res 4
% space for variable t79
t79     res 4
% space for variable t80
t80     res 4
% space for t79 * t80
t81        res 4
% space for n - t81
t82        res 4
% space for variable t83
t83     res 4
% space for variable t84
t84     res 4
% space for t83 / t84
t85        res 4
% space for n * t85
t86        res 4
% space for variable t87
t87     res 4
% space for variable t88
t88     res 4
% space for t87 / t88
t89        res 4
% space for n * t89
t90        res 4
% space for variable t91
t91     res 4
% space for t90 < t91
t92        res 4
% space for variable t93
t93     res 4
% space for variable t94
t94     res 4
% space for t93 / t94
t95        res 4
% space for n * t95
t96        res 4
% space for t96 < i
t97        res 4
% space for variable t98
t98     res 4
% space for n + t98
t99        res 4
% space for variable t100
t100    res 4
% space for t99 * t100
t101       res 4
% space for variable t102
t102    res 4
% space for t102 * i
t103       res 4
% space for variable t104
t104    res 4
% space for t103 / t104
t105       res 4
% space for n + t105
t106       res 4
% space for variable t107
t107    res 4
% space for  + 
t108       res 4
% space for variable t109
t109    res 4
% space for t109 - 
t110       res 4
% space for variable t111
t111    res 4
% space for t111 - 
t112       res 4
% space for variable t113
t113    res 4
% space for t113 * 
t114       res 4
% space for  * 
t115       res 4
% space for t115 + i
t116       res 4
% space for variable t117
t117    res 4
% space for not t117
t118       res 4
% space for t118 < i
t119       res 4
% space for variable t120
t120    res 4
% space for variable t121
t121    res 4
% space for t121 and i
t122       res 4
% space for t120 < t122
t123       res 4
% space for variable t124
t124    res 4
% space for variable t125
t125    res 4
% space for t125 and i
t126       res 4
% space for t124 < t126
t127       res 4
% space for variable t128
t128    res 4
% space for array value
t129       res 4
% space for variable t130
t130    res 4
% space for t129 * t130
t131       res 4
% space for variable t132
t132    res 4
% space for i * t132
t133       res 4
% space for array value
t134       res 4
% space for t131 + t134
t135       res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for function parameter arr
arr     res 4
% space for function parameter size
size    res 4
% space for variable n
n       res 4
% space for n + 
t136       res 4
% space for function parameter x
x       res 4
% space for variable i
i       res 4
% space for variable t137
t137    res 4
% space for variable t138
t138    res 4
% space for array value
t139       res 4
% space for variable t140
t140    res 4
% space for array value
t141       res 4
% space for variable t142
t142    res 4
% space for variable t143
t143    res 4
% space for variable t144
t144    res 4
% space for i + t144
t145       res 4
% space for array value
t146       res 4
% space for variable t147
t147    res 4
% space for i + t147
t148       res 4
% space for function parameter x
x       res 4
% space for variable t149
t149    res 4
% space for function parameter x
x       res 4
% space for variable i
i       res 4
% space for variable i
i       res 4
% space for variable t150
t150    res 4
% space for variable i
i       res 4
% space for variable t151
t151    res 4
% space for variable i
i       res 4
% space for variable t152
t152    res 4
% space for variable i
i       res 4
% space for variable t153
t153    res 4
% space for variable t154
t154    res 4
% space for variable i
i       res 4
% space for variable t155
t155    res 4
% space for variable i
i       res 4
% space for variable t156
t156    res 4
% space for variable t157
t157    res 4
% space for variable t158
t158    res 4
% space for variable i
i       res 4
% space for variable t159
t159    res 4
