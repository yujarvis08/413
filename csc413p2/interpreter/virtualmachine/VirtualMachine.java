package interpreter.virtualmachine;

import java.util.Stack;
import interpreter.bytecode.*;

public class VirtualMachine {

    private RunTimeStack runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        dumpON;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void halt() {
        isRunning = false;
    }
    public void setProgramCounter(int value) {
        programCounter = value;
    }
    public void setDumpState(boolean status) {
        dumpON = status;
    }
    public void setArguments(int count) {
        runTimeStack.setArguments(count);
    }
    public int  peekRTS() {
        return runTimeStack.peek();
    }
    public void pushRTS(int p) {
        runTimeStack.push(p);
    }
    public void pushProgramCounter(int value) {
        returnAddress.push(value);
    }
    public int  popRTS() {
        return runTimeStack.pop();
    }
    public void popFPS() {
        runTimeStack.popFPS();
    }
    public void storeRTS(int offsetFromFramePointer) {
        runTimeStack.store(offsetFromFramePointer);
    }
    public void storeProgramCounter() { pushProgramCounter(programCounter); }
    public void loadRTS(int offsetFromFramePointer) {
        runTimeStack.load(offsetFromFramePointer);
    }
    public void newFrameAt(int offsetFromFramePointer) {
        runTimeStack.newFrameAt(offsetFromFramePointer);
    }
    public void returnProgramCounter() {
        setProgramCounter(returnAddress.pop());
    }
    public void printRTSArguments() { runTimeStack.printRTSArguments();}

    public void executeProgram() {
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;
        dumpON = true;

        while (isRunning) {
            ByteCode bc = program.getCode(programCounter);
            bc.runVM(this);
            if (dumpON && !(bc instanceof DumpCode)) {
                bc.print(this);
                runTimeStack.dump();
            }
            programCounter++;
        }

    }

}
