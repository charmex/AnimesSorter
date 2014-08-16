/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorterino;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Sorter {

    /**
     * @param args the command line arguments
     */
    private File[] listFiles;
    private File dir;
    private ArrayList<String> names;
    private ArrayList<File> directories;

    public Sorter(File f) throws IOException {
        names = new ArrayList();
        directories = new ArrayList();
        dir = f;
        listFiles = dir.listFiles();
        process();
    }

    private void process() throws IOException {
        findFolders();
        createFolders();
        moveFiles();
    }

    /*
     Find potential and existent folder names
     Potential folder names go to @names
     Existent folders goto @directories
     */
    public void findFolders() {
        System.out.println("------Find folders");
        for (File f : listFiles) {
            String name = f.getName();
            System.out.println("Filename = " + name + ", filename size = " + name.length());
            String[] split = name.split("\\.");
            if (split.length > 1 && split[1].equals("jpg")) {
                System.out.println("Skipping wrong file");
                continue;
            }
            if (f.getName().equals("")) {
                continue;
            }

            if (f.isDirectory()) {
                directories.add(f);
            } else {
                System.out.println("Filternig: " + f.getAbsolutePath());
                String s = getFilteredName(f.getName());
                if (!names.contains(s)) {
                    names.add(s);
                }
            }
        }
        System.out.println("----- Result");
        for (String s : names) {
            System.out.println(s);
        }
    }

    /*
     Create non-existent directories
     Created directories go to @directories
     */
    public void createFolders() {
        System.out.println("--------Creating folders");
        for (int i = 0; i < names.size(); i++) {
            File f = new File(dir.getAbsolutePath(), names.get(i));
            if (directories.contains(f)) {
                System.out.println("Already existent folder: " + f.getAbsolutePath());
                continue;
            }
            boolean mkdir = f.mkdir();
            System.out.println("Creating folder: " + f.getAbsolutePath());
            if (mkdir) {
                directories.add(f);
            } else {
                System.out.println("Error while creating folder: " + f.getAbsolutePath());
            }
        }
    }

    public void moveFiles() throws IOException {
        /*
         Move files to their respective folder
         */
        System.out.println("Moving files now");
        for (File f : listFiles) {
            if (!f.isDirectory()) {
                String name = f.getName();
                String[] split = name.split("\\.");
                if (split.length > 1 && split[1].equals("jpg")) {
                    System.out.println("Skipping wrong file");
                    continue;
                }
                if (f.getName().equals("")) {
                    continue;
                }

                String s = getFilteredName(f.getName());
                if (s.isEmpty()) {
                    continue;
                }
                System.out.println("Processing file: " + f.getName() + ", filtered name: " + s);
                File chk = new File(dir.getAbsolutePath(), s);
                System.out.println("Checking if folder exists: " + chk.getAbsolutePath());
                File n = new File(chk, f.getName());
                if (directories.contains(chk)) {
                    System.out.println("Moving from: " + f.toPath() + ", to path: " + chk.toPath());
                    Files.move(f.toPath(), n.toPath());

                }
            }
        }
    }

    public static String getFilteredName(String filename) {
        String[] split = filename.split(" ");
        ArrayList<String> fileName = new ArrayList();
        ArrayList<String> f = new ArrayList();
        Collections.addAll(fileName, split);
        Collections.addAll(f, split);
        boolean smash = false;
        for (String s : f) {
            if (s.contains("[") || s.contains("]")) {
                fileName.remove(s);
            }
            if (s.equals("-")) {
                smash = true;
            }

            if (smash) {
                fileName.remove(s);
            }
        }

        if (fileName.size() == 0) {
            return "";
        }
        String fullname = "";
        for (String s : fileName) {
            fullname = fullname.concat(s).concat(" ");
        }
        fullname = (String) fullname.subSequence(0, fullname.length() - 1);
//        System.out.println(fullname);
        return fullname;
    }

    public static void main(String[] args) {
        /*
         Init
         */
        FrmMain fm = new FrmMain();
//        File dir = new File("F:\\Season");
//        File[] listFiles = dir.listFiles();
//        ArrayList<String> names = new ArrayList();
//        ArrayList<File> directories = new ArrayList();
        /*
         Find potential and existent folder names
         Potential folder names go to @names
         Existent folders goto @directories
         */
//        System.out.println("------Find folders");
//        for (File f : listFiles) {
//            String name = f.getName();
//            System.out.println("Filename = " + name + ", filename size = " + name.length());
//            String[] split = name.split("\\.");
//            if (split.length > 1 && split[1].equals("jpg")) {
//                System.out.println("Skipping wrong file");
//                continue;
//            }
//            if (f.getName().equals("")) {
//                continue;
//            }
//
//            if (f.isDirectory()) {
//                directories.add(f);
//            } else {
//                System.out.println("Filternig: " + f.getAbsolutePath());
//                String s = getFilteredName(f.getName());
//                if (!names.contains(s)) {
//                    names.add(s);
//                }
//            }
//        }
//        System.out.println("----- Result");
//        for (String s : names) {
//            System.out.println(s);
//        }

        /*
         Create non-existent directories
         Created directories go to @directories
         */
//        System.out.println("--------Creating folders");
//        for (int i = 0; i < names.size(); i++) {
//            File f = new File(dir.getAbsolutePath(), names.get(i));
//            if (directories.contains(f)) {
//                System.out.println("Already existent folder: " + f.getAbsolutePath());
//                continue;
//            }
//            boolean mkdir = f.mkdir();
//            System.out.println("Creating folder: " + f.getAbsolutePath());
//            if (mkdir) {
//                directories.add(f);
//            } else {
//                System.out.println("Error while creating folder: " + f.getAbsolutePath());
//            }
//        }
    }

}
