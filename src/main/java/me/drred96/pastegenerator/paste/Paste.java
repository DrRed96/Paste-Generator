package me.drred96.pastegenerator.paste;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Paste {

    public StringBuilder builder = new StringBuilder();

    public String title = "some loser";
    public String author = "probably not kt";
    public int width = 150;
    public int margin = 20;

    private final List<Component> components = new ArrayList<>();

    public void write(String path) throws IOException {
        for (Component component : components) {
            component.write();
        }

        FileWriter writer = new FileWriter(path);
        writer.write(builder.toString());
        writer.close();

    }

    public void load(String path) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File file = new File(path);

        JsonObject root = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();

        title = root.get("title").getAsString();
        author = root.get("author").getAsString();
        if (root.has("width")) width = root.get("width").getAsInt();
        if (root.has("margin")) margin = root.get("margin").getAsInt();

        if (root.has("components")) {
            JsonArray jsonComponents = root.getAsJsonArray("components");
            for (JsonElement element : jsonComponents) {
                JsonObject jsonComponent = element.getAsJsonObject();
                String type = jsonComponent.get("type").getAsString();
                Class<? extends Component> componentClass = Component.COMPONENTS.getOrDefault(type, null);
                if (componentClass != null) {
                    Component component = componentClass.getDeclaredConstructor(Paste.class).newInstance(this);
                    component.deserialize(jsonComponent);
                    components.add(component);
                }
            }
        }
    }

    public String margin() {
        return " ".repeat(margin);
    }

}
