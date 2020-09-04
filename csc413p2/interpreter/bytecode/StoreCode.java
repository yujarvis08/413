package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class StoreCode extends ByteCode {

    private int value;
    private int peek;
    private String id;

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            id = args.get(2);
        }
    }

    @Override
    public void runVM(VirtualMachine vm) {
        peek = vm.peekRTS();
        vm.storeRTS(value);
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("STORE " +value+" "+id+"  "+id+" = "+peek);
    }
}
