
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {

        String line;
        String[] items;
        ArrayList<ByteCode> args = new ArrayList<>();
        String className; // class name after its mapped from name in source code to class name
        String byteCodeName;// ByteCode name from .x.cod file
        Class classBlueprint;
        Program program = new Program(args);
        ByteCode bc;

        try {
            while (this.byteSource.ready()) {
                //tokenize read line
                line = this.byteSource.readLine();
                items = line.split("\\s+");
                ArrayList<String> stringTokens = new ArrayList<>(Arrays.asList(line.split(" ")));
                //grab first token of line
                byteCodeName = items[0];
                //grab class name from token
                className = CodeTable.getClassName(byteCodeName);
                //load class blueprint from class name
                classBlueprint = Class.forName("interpreter.bytecode." + className);
                //get declared constructor
                //create new instance of bytecode using constructor
                bc = (ByteCode) classBlueprint.getDeclaredConstructor().newInstance();
                //grab remaining arguments
                bc.init(stringTokens);

                args.add(bc);
            }
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(255);
        } catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        program.resolveAddress();
        return program;
    }
}
