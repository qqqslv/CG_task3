package ru.vsu.cs.kovalyov_v_m;

import ru.vsu.cs.kovalyov_v_m.model.Model;
import ru.vsu.cs.kovalyov_v_m.obj_reader.ObjReader;
import ru.vsu.cs.kovalyov_v_m.obj_writer.ObjWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static Model read(String filename) throws IOException {
        Path fileName = Path.of("models/" + filename);
        String fileContent = Files.readString(fileName);
        System.out.println("Loading model " + filename);
        return ObjReader.read(fileContent);
    }

    public static void main(String[] args) throws IOException {
        String ORIG_FOLDER = "orig/";
        String RES_FOLDER = "result/";
        File folder = new File("models/" + ORIG_FOLDER);
        ObjWriter objWriter = new ObjWriter();

        for (File file : folder.listFiles()) {
            Model model1 = read(ORIG_FOLDER + file.getName());
            objWriter.write(model1, "models/" + RES_FOLDER + file.getName());
            Model model2 = read(RES_FOLDER + file.getName());
            if (!model1.equals(model2)) {
                System.out.println("Модель изменилась!!!: " + file.getName());
                return;
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
