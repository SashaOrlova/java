package ru.java.homework;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

public class ZipFile {
    /** find all zip archives matches with regex and unzip them in directory
     * src/test/resources/result/ + nameOfFile.
     * @param pathName directory where zip file locate
     * @param regex only files matches with regex will be unzip
     * @throws IOException
     */
    private static void findZip(String pathName, String regex) throws IOException{
        File myFolder = new File(pathName);
        File[] files = myFolder.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                findZip(f.getAbsolutePath(), regex);
            } else {
                if (f.isFile() && f.getName().matches(regex) && f.getName().matches(".*.zip"))
                    try (FileSystem file = FileSystems.newFileSystem(f.toPath(), null)) {
                        try {
                            for (Enumeration<? extends ZipEntry> enumZip = new
                                    java.util.zip.ZipFile(f).entries(); enumZip.hasMoreElements();) {
                                ZipEntry entry = enumZip.nextElement();
                                try {
                                    Files.copy(file.getPath(entry.getName()),
                                        Paths.get("src/test/resources/result/" + entry.getName()),
                                        StandardCopyOption.REPLACE_EXISTING);
                                }
                                catch (IOException e) {
                                    System.out.println(e.getMessage());
                                    throw e;
                                }
                            }
                        }
                        catch (IOException e) {
                            System.out.println(e.getMessage());
                            throw e;
                        }
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 2)
            try {
                findZip(args[1], args[2]);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        else
            System.out.println("Wrong number of args!");
    }
}