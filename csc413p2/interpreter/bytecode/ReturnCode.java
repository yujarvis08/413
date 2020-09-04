package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    private String value;
    private String id;

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 1) {
            value = args.get(1);
            id = args.get(1).split("<<", 2)[0];
        }
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.popFPS();
        vm.returnProgramCounter();
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.print("RETURN");
        System.out.println();
    }
}
