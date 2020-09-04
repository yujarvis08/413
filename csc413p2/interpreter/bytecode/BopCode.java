package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {

    private String operation;

    @Override
    public void init(ArrayList<String> args) {
        operation = args.get(1);
    }

    @Override
    public void runVM(VirtualMachine vm) {
        int pop2 = vm.popRTS();
        int pop1 = vm.popRTS();

        if (operation.equals("+")) { vm.pushRTS(pop1 + pop2); }
        else if (operation.equals("-")) { vm.pushRTS(pop1 - pop2); }
        else if (operation.equals("/")) { vm.pushRTS(pop1 / pop2); }
        else if (operation.equals("*")) { vm.pushRTS(pop1 * pop2); }
        else if (operation.equals("==")) {
            if(pop1 == pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals("!=")) {
            if(pop1 != pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals("<=")) {
            if(pop1 <= pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals(">")) {
            if(pop1 > pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals(">=")) {
            if(pop1 >= pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals("<")) {
            if(pop1 < pop2) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals("|")) {
            if(pop1 == 1 || pop2 == 1) {
                vm.pushRTS(1);
            } else vm.pushRTS(0);
        }
        else if (operation.equals("&")) {
            if(pop1 == 1 && pop2 == 1) {
                vm.pushRTS(1);}
        } else vm.pushRTS(0);
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("BOP " + operation);
    }
}
