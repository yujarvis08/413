package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;
    private int arguments;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void dump() {
        int subListElement = 0;

        if(!framePointer.isEmpty()) {
            for(int i = 0; i < framePointer.size() - 1; i++) {
                System.out.println(runTimeStack.subList(subListElement, framePointer.get(i)));
                subListElement = framePointer.get(i);
            }
        }
        System.out.println(runTimeStack.subList(subListElement, runTimeStack.size()));
    }

    public int peek() {
        return this.runTimeStack.get(this.runTimeStack.size()-1);
    }

    public int pop() {
        if (!runTimeStack.isEmpty()) {
            return this.runTimeStack.remove(this.runTimeStack.size()-1);
        } else {
            return 0;
        }
    }

    public void newFrameAt(int offsetFromFramePointer) {
        framePointer.push(runTimeStack.size() - offsetFromFramePointer);
    }

    public void popFPS() {
        int pop = this.pop();
        int fps = framePointer.pop();
        for (int i = runTimeStack.size() - 1; i >= fps; i--) {
            pop();
        }
        push(pop);
    }

    public int store(int offsetFromFramePointer) {
        int pop = this.pop();
        runTimeStack.set(framePointer.peek() + offsetFromFramePointer, pop);
        return pop;
    }

    public int load(int offsetFromFramePointer) {
        int loadedValue = runTimeStack.get(framePointer.peek() + offsetFromFramePointer);
        push(loadedValue);
        return loadedValue;
    }

    public int push(int valueToPush) {
        this.runTimeStack.add(valueToPush);
        return this.peek();
    }

    public void printRTSArguments() {
        if (!runTimeStack.isEmpty()) {
            for (int i = 0; i < arguments; i++) {
                System.out.print(runTimeStack.get(runTimeStack.size() - arguments + i));
            }
        }
    }

    public void setArguments(int a) {
        arguments = a;
    }

}
