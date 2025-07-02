/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.study.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class ArchiveController {
    private final Logger logger = LogManager.getLogger(ArchiveController.class);

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void saveFile(String text, Component parent) {
        if (this.file == null) {
            this.chooseFile(parent);
            if(this.getFile() == null) return;
            this.saveNewFile();
        }
        this.writeFile(text);
    }
    public void generateIlFile(String sourceFilePath, String ilCode) throws IOException {
        if (sourceFilePath == null || sourceFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo fonte não pode ser nulo ou vazio.");
        }
        Objects.requireNonNull(ilCode, "O conteúdo do código IL não pode ser nulo.");

        File sourceFile = new File(sourceFilePath);

        String parentPath = sourceFile.getParent();
        if (parentPath == null) {
            parentPath = ".";
        }
        File parentDir = new File(parentPath);

        String fileName = sourceFile.getName();
        String baseName = getFileNameWithoutExtension(fileName);

        String ilFileName = baseName + ".il";

        File destinationFile = new File(parentDir, ilFileName);

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(destinationFile), StandardCharsets.UTF_8)) {
            writer.write(ilCode);
        }

        this.file = destinationFile;
        logger.info("Arquivo objeto gerado com sucesso em: {}", destinationFile.getAbsolutePath());
    }

    private String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
    }


    public void saveNewFile() {
        if(this.getFile() == null) return;
        if (this.file.getPath().endsWith(".txt")) {
            file = new File(this.file.getPath());
        } else {
            file = new File(this.file.getPath() + ".txt");
        }
    }

    public void chooseFile(Component parent) {
        JFileChooser chooseArchiveScreen = new JFileChooser();

        if (getFile() != null) {
            chooseArchiveScreen.setCurrentDirectory(new File(this.file.getAbsolutePath()));
        }

        chooseArchiveScreen.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        if (chooseArchiveScreen.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            this.setFile(chooseArchiveScreen.getSelectedFile());
        }
    }

    public void writeFile(String text) {
        if(text == null || text.isBlank()) return;
        try(FileWriter writer = new FileWriter(this.file)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String readFile() {
        StringBuilder text = new StringBuilder();

        try(Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append("\r\n");
            }
            return text.toString();
        } catch (Exception e) {
            return "Não foi possível abrir o arquivo: " + e.getMessage();
        }
    }
}