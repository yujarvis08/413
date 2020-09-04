package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {

    private String id;
    private int location;

    @Override
    public void init(ArrayList<String> args) {
        id = args.get(1);
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.setProgramCounter(location - 1);
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("GOTO " + id);
    }

    public String returnId() {
        return id;
    }

    public void setLocation(int a) {
        location = a;
    }
}
