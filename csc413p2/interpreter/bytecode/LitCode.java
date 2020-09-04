package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;


public class LitCode extends ByteCode {

    private int value;
    private String id;

    @Override
    public void init(ArrayList<String> args) {
        this.value = Integer.parseInt(args.get(1));
        if(args.size() > 2) this.id = args.get(2);
    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.pushRTS(value);
    }
    @Override
    public void print(VirtualMachine vm) {
        String base = "LIT " + value;
        if (id != null) {
            base += " int " + id;
        }
        System.out.println(base);
    }
}
