package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.bytecode.*;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {

        program = new ArrayList<>();
    }

    public Program(ArrayList<ByteCode> loadedByteCodes) {
        program = loadedByteCodes;
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        HashMap<String, Integer> address = new HashMap<>();
        for (int i = 0; i < program.size(); i++) {
            ByteCode bc = program.get(i);
            if (bc instanceof LabelCode) {
                address.put(((LabelCode) bc).returnId(), i);
            }
        }
        for (int i = 0; i < program.size(); i++) {
            ByteCode bc = program.get(i);
            if (bc instanceof FalseBranchCode) {
                ((FalseBranchCode) bc).setLocation(address.get(((FalseBranchCode) bc).returnId()));
            } else if (bc instanceof CallCode) {
                ((CallCode) bc).setLocation(address.get(((CallCode) bc).returnId()));
            } else if (bc instanceof GotoCode) ((GotoCode) bc).setLocation(address.get(((GotoCode) bc).returnId()));
        }
    }
}
