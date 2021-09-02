# Kotlin Compiler Bug Repro

Small repository to reproduce a bug in the Kotlin 1.5.30 compiler.

To reproduce:

```
mvn clean verify -Dkotlin.version=1.5.21
```
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running BugTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.149 s - in BugTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
```
mvn clean verify -Dkotlin.version=1.5.30
```
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running BugTest
[ERROR] Tests run: 4, Failures: 0, Errors: 2, Skipped: 0, Time elapsed: 0.16 s <<< FAILURE! - in BugTest
[ERROR] fails with Kotlin 1_5_30 (A)(BugTest)  Time elapsed: 0.027 s  <<< ERROR!
java.lang.VerifyError:
Bad type on operand stack
Exception Details:
  Location:
    BugTestKt.onErrorFail(Lkotlin/Pair;)Lkotlin/Pair; @33: athrow
  Reason:
    Type 'java/lang/Object' (current frame, stack[0]) is not assignable to 'java/lang/Throwable'
  Current Frame:
    bci: @33
    flags: { }
    locals: { 'kotlin/Pair', 'kotlin/Pair', integer, integer, 'kotlin/Pair', integer }
    stack: { 'java/lang/Object' }
  Bytecode:
    0000000: 2a12 0ab8 0010 2a4c 033d 033e 2b3a 0403
    0000010: 3605 2ab6 002e c600 0c2a b600 2e59 b800
    0000020: 1cbf 2bb0
  Stackmap Table:
    full_frame(@34,{Object[#20],Object[#20],Integer,Integer,Object[#20],Integer},{})

        at BugTest.fails with Kotlin 1_5_30 (A)(BugTest.kt:22)

[ERROR] fails with Kotlin 1_5_30 (B)(BugTest)  Time elapsed: 0 s  <<< ERROR!
java.lang.VerifyError:
Bad type on operand stack
Exception Details:
  Location:
    BugTestKt.onErrorFail(Lkotlin/Pair;)Lkotlin/Pair; @33: athrow
  Reason:
    Type 'java/lang/Object' (current frame, stack[0]) is not assignable to 'java/lang/Throwable'
  Current Frame:
    bci: @33
    flags: { }
    locals: { 'kotlin/Pair', 'kotlin/Pair', integer, integer, 'kotlin/Pair', integer }
    stack: { 'java/lang/Object' }
  Bytecode:
    0000000: 2a12 0ab8 0010 2a4c 033d 033e 2b3a 0403
    0000010: 3605 2ab6 002e c600 0c2a b600 2e59 b800
    0000020: 1cbf 2bb0
  Stackmap Table:
    full_frame(@34,{Object[#20],Object[#20],Integer,Integer,Object[#20],Integer},{})

        at BugTest.fails with Kotlin 1_5_30 (B)(BugTest.kt:29)

[INFO]
[INFO] Results:
[INFO]
[ERROR] Errors:
[ERROR]   BugTest.fails with Kotlin 1_5_30 (A):22 Verify Bad type on operand stack
Excep...
[ERROR]   BugTest.fails with Kotlin 1_5_30 (B):29 Verify Bad type on operand stack
Excep...
[INFO]
[ERROR] Tests run: 4, Failures: 0, Errors: 2, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```
