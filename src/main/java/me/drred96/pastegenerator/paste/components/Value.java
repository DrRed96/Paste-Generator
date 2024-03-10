package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

public class Value extends Component {

    String name;
    String value;

    public Value(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {
        //      » Full Name:            Jade Louise Call
        String front = " ".repeat(5) + "» " + name + ": ";
        paste.builder
                .append(paste.margin())
                .append(front)
                .append(" ".repeat(29 - front.length()))
                .append(value)
                .append("\n");
    }

    @Override
    public void deserialize(JsonObject object) {
        name = object.get("name").getAsString();
        value = object.get("value").getAsString();
    }
}
