package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class HaltCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) { }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.halt();
    }
    @Override
    public void print(VirtualMachine vm) { System.out.println("HALT"); }
}
