package me.djaydenr.holopassporttsmp.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileEditor {

    public static File copyInputStreamToFile(InputStream inputStream) {
        File file = new File("./create.sql");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            // handle exception here
        }
        return file;
    }

    public static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
