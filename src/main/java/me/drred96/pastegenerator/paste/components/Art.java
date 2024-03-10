package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Art extends Component {

    String path;
    Alignment alignment = Alignment.LEFT;

    public Art(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {
        try {
            // Read the file into lines
            List<String> lines = new ArrayList<>();
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
            reader.close();

            int centerMargin = 0;
            if (alignment == Alignment.CENTER) {
                for (String line : lines) {
                    if (line.length() > centerMargin) {
                        centerMargin = line.length();
                    }
                }
                centerMargin = (paste.width - centerMargin) / 2;
            }

            for (String line : lines) {
                switch (alignment) {
                    case LEFT -> paste.builder.append(line).append("\n\n");
                    case CENTER -> paste.builder.append(" ".repeat(centerMargin)).append(line).append("\n");
                }
            }

            paste.builder.append("\n");

        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deserialize(JsonObject object) {
        path = object.get("path").getAsString();
        if (object.has("alignment")) alignment = Alignment.valueOf(object.get("alignment").getAsString().toUpperCase());
    }

    public enum Alignment {
        LEFT,
        CENTER
    }

}
