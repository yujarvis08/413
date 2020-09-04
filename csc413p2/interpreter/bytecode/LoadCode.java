package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {

    private int value;
    private String id;

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(1));
        if (args.size() > 2) id = args.get(2);

    }

    @Override
    public void runVM(VirtualMachine vm) {
        vm.loadRTS(value);
    }
    @Override
    public void print(VirtualMachine vm) {
        String base = "LOAD " + value;
        if (id != null) {
            base += " " + id + " <load " + id + ">";
        }
        System.out.println(base);
    }
}
