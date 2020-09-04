package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {

    private boolean status;

    @Override
    public void init(ArrayList<String> args) {
        if(args.get(1).equals("ON")) {
            status = true;
        } else {
            status = false;
        }
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.setDumpState(status);
    }
    @Override
    public void print(VirtualMachine vm) {
    }
}
