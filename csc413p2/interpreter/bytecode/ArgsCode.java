package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    private int arguments;

    @Override
    public void init(ArrayList<String> args) {
        arguments = Integer.parseInt(args.get(1));
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.newFrameAt(arguments);
    }

    @Override
    public void print(VirtualMachine vm) {
        System.out.println("ARGS " + arguments);
        vm.setArguments(arguments);
    }
}
