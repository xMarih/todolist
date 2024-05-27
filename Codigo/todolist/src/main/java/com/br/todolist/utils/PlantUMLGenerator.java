package com.br.todolist.utils;

import net.sourceforge.plantuml.SourceFileReader;
import java.io.File;
import java.io.IOException;

public class PlantUMLGenerator {
    public static void main(String[] args) {
        generateDiagram("src/docs/diagramas_sequencia/createTask.puml");
        generateDiagram("src/docs/diagramas_sequencia/createTaskWithDoDate.puml");
        generateDiagram("src/docs/diagramas_sequencia/createTaskWtihDeadLine.puml");
        generateDiagram("src/docs/diagramas_sequencia/deleteTask.puml");
        generateDiagram("src/docs/diagramas_sequencia/getAllFreeTasks.puml");
        generateDiagram("src/docs/diagramas_sequencia/getAllTask.puml");
        generateDiagram("src/docs/diagramas_sequencia/getTaskById.puml");
        generateDiagram("src/docs/diagramas_sequencia/getTasksWithDeadline.puml");
        generateDiagram("src/docs/diagramas_sequencia/getTasksWithDoDate.puml");
        generateDiagram("src/docs/diagramas_sequencia/updateTask.puml");
    }

    public static void generateDiagram(String filePath) {
        try {
            File source = new File(filePath);
            if (!source.exists()) {
                System.err.println("File not found: " + filePath);
                return;
            }
            SourceFileReader reader = new SourceFileReader(source);
            reader.getGeneratedImages();
            System.out.println("Diagram generated for " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating diagram for " + filePath);
            e.printStackTrace();
        }
    }
}
