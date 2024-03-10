package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

import java.util.ArrayList;
import java.util.List;

public class IndexedValueList extends Component {

    int index;
    String name;
    List<String[]> values = new ArrayList<>();

    public IndexedValueList(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {
        paste.builder.append(paste.margin()).append(" ".repeat(5)).append("» [").append(index).append("]: ").append(name).append("\n");

        for (String[] value : values) {
            String front = " ".repeat(12) + "- " + value[0] + ":";
            paste.builder
                    .append(paste.margin())
                    .append(front)
                    .append(" ".repeat(29 - front.length()))
                    .append(value[1])
                    .append("\n");
        }
        paste.builder.append("\n");
    }

    @Override
    public void deserialize(JsonObject object) {
        index = object.get("index").getAsInt();
        name = object.get("name").getAsString();
        JsonArray values = object.get("values").getAsJsonArray();
        for (JsonElement element : values) {
            JsonObject valueObj = element.getAsJsonObject();
            this.values.add(new String[]{
                    valueObj.get("name").getAsString(),
                    valueObj.get("value").getAsString()
            });
        }
    }
}
