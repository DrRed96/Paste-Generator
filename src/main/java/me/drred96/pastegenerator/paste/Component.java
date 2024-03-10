package me.drred96.pastegenerator.paste;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.components.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Component {

    public static final Map<String, Class<? extends Component>> COMPONENTS = new HashMap<>();

    protected final Paste paste;

    public Component(Paste paste) {
        this.paste = paste;
    }

    public abstract void write();

    public abstract void deserialize(JsonObject object);

    static {
        COMPONENTS.put("art", Art.class);
        COMPONENTS.put("indexed_value_list", IndexedValueList.class);
        COMPONENTS.put("list", ListComponent.class);
        COMPONENTS.put("new_line", NewLine.class);
        COMPONENTS.put("paragraph", Paragraph.class);
        COMPONENTS.put("section_header", SectionHeader.class);
        COMPONENTS.put("value", Value.class);
        COMPONENTS.put("value_list", ValueList.class);
    }

}
