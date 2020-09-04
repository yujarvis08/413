package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void runVM(VirtualMachine vm) {
        System.out.println(vm.peekRTS());
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("WRITE");
    }
}
