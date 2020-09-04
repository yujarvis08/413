package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {

    private String id;
    private int location;

    @Override
    public void init(ArrayList<String> args) {
        id = args.get(1);
    }

    @Override
    public void runVM(VirtualMachine vm) {
        if (vm.popRTS() == 0) {
            vm.setProgramCounter(location - 1);
        }
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("FALSEBRANCH " + id);
    }

    public String returnId() {
        return id;
    }

    public void setLocation(int a) {
        location = a;
    }
}
