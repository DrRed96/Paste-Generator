package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

public class NewLine extends Component {

    int amount = 1;

    public NewLine(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {
        paste.builder.append("\n".repeat(amount));
    }

    @Override
    public void deserialize(JsonObject object) {
        if (object.has("amount")) {
            amount = object.get("amount").getAsInt();
        }
    }
}
