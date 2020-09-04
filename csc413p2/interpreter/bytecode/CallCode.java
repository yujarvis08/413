package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {

    private String id;
    private int address;
    private String baseID;

    @Override
    public void init(ArrayList<String> args) {
        id = args.get(1);
        baseID = args.get(1).split("<<", 2)[0];
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.storeProgramCounter();
        vm.setProgramCounter(address - 1);
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.print("CALL " + id + "  " + baseID + "(");
        vm.printRTSArguments();
        System.out.print(")");
    }

    public String returnId() {
        return id;
    }

    public void setLocation(int a) {
        address = a;
    }
}
