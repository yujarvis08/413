package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;


public class ReadCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) { }

    @Override
    public void runVM(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter an int: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter an int: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        vm.pushRTS(input);
    }
    @Override
    public void print(VirtualMachine vm) {
        System.out.println("READ");
    }
}
