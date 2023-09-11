import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String extension = ".txt";
        String path = File.separator + "Users" + File.separator + "willy" + File.separator + "Desktop" + File.separator;
        String directoryName = JOptionPane.showInputDialog(null, "Escribe el nombre del directorio");
        String directoryPath = path + directoryName;
        String fileName = JOptionPane.showInputDialog(null, "Escribe el nombre del archivo sin extension");
        String fileNameAndExtension = fileName + extension;
        String filePath = directoryPath + File.separator + fileNameAndExtension;
        String phrase = JOptionPane.showInputDialog(null, "Escribe una frase para rellenar el archivo");

        try {
            createDirectory(directoryPath);
            createFile(filePath);
            writePhrase(directoryPath, fileNameAndExtension, phrase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
            JOptionPane.showMessageDialog(null, "Directorio creado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El directorio ya existe");
        }
    }

    static void createFile(String path) throws IOException {
        File newFile = new File(path);
        if (!newFile.exists()) {
            newFile.createNewFile();
            JOptionPane.showMessageDialog(null, "Archivo creado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El archivo ya existe");
        }

    }

    static void writePhrase(String path, String file, String phrase) throws IOException {
        File directoryFile = new File(path);
        String[] filesDirList = directoryFile.list();

        if (filesDirList != null) {
            for (String element : filesDirList) {
                File files = new File(directoryFile.getAbsolutePath(), element);
                if (files.isFile() && files.getName().equals(file)) {
                    System.out.println("Estoy dentro del if del files");
                    FileWriter writer = new FileWriter(files.getAbsolutePath());
                    for (int i = 0; i < phrase.length(); i++){
                        writer.write(phrase.charAt(i));
                    }
                    JOptionPane.showMessageDialog(null, "Frase escrita");
                    writer.close();
                }
            }
        } else System.out.println("array vacio");
    }
}