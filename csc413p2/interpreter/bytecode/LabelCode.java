package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {

    private String id;

    @Override
    public void init(ArrayList<String> args) { id = args.get(1); }

    @Override
    public void runVM(VirtualMachine vm) { }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("LABEL " + id);
    }

    public String returnId() {
        return id;
    }
}
