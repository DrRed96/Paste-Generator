package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends Component {

    public String text = "Hello!";
    public Alignment alignment = Alignment.CENTER;

    public Paragraph(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {

        StringBuilder builder = new StringBuilder();
        List<String> lines = new ArrayList<>();
        String[] words = text.split("\\s+");
        boolean first = true;
        for (String word : words) {
            if (first) {
                builder.append(word);
                first = false;
                continue;
            }
            if (builder.length() + word.length() + 1 > (alignment == Alignment.CENTER_SMALL && !lines.isEmpty() ? lines.get(lines.size() - 1).length() : paste.width - 6)) {
                lines.add(builder.toString());
                builder = new StringBuilder(word);
                continue;
            }
            builder.append(" ").append(word);
        }
        if (!builder.isEmpty()) {
            lines.add(builder.toString());
        }

        for (String line : lines) {
            switch (alignment) {
                case LEFT -> paste.builder.append(paste.margin()).append(" ".repeat(3)).append(line).append("\n");
                case CENTER, CENTER_SMALL -> paste.builder.append(paste.margin()).append(" ".repeat((paste.width - 6 - line.length()) / 2 + 3)).append(line).append("\n");
            }
        }
        paste.builder.append("\n");


    }

    @Override
    public void deserialize(JsonObject object) {
        text = object.get("text").getAsString();
        if (object.has("alignment")) {
            alignment = Alignment.valueOf(object.get("alignment").getAsString().toUpperCase());
        }
    }

    public enum Alignment {
        LEFT,
        CENTER,
        CENTER_SMALL
    }

}
