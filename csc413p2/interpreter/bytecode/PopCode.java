package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class PopCode extends ByteCode {

    private int arguments;

    @Override
    public void init(ArrayList<String> args) {
        arguments = Integer.parseInt(args.get(1));
    }

    @Override
    public void runVM(VirtualMachine vm) {
        for (int i = 0; i < arguments; i++) {
            vm.popRTS();
        }
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("POP " + arguments);
    }
}
