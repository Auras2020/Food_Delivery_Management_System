package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

    private File outFile;

    public FileWrite(File outFile){
        this.outFile=outFile;
    }

    public FileWriter createFWriter(){
        FileWriter fWriter;
        try{
            outFile.createNewFile();
            fWriter= new FileWriter(outFile);
        }catch (IOException ex){
            System.out.println("error creating fWriter");
            return null;
        }
        return fWriter;
    }
}
