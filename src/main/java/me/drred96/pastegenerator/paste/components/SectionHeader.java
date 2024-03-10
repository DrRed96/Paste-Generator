package me.drred96.pastegenerator.paste.components;

import com.google.gson.JsonObject;
import me.drred96.pastegenerator.paste.Component;
import me.drred96.pastegenerator.paste.Paste;

public class SectionHeader extends Component {

    /**
     * Basic example based off of Networks design (https://doxbin.net/user/Network)
     * ╔════════════════════════════════════════════════════════════════════╗
     * ║[0x00 - Section - Title]▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█[Author]║
     * ╚════════════════════════════════════════════════════════════════════╝
     */

    int section;
    int subSection;
    String title;


    public SectionHeader(Paste paste) {
        super(paste);
    }

    @Override
    public void write() {

        String front = String.format("[%xx%02x - %s - %s]", section, subSection, title, paste.title);
        String back = "[" + paste.author + "]";

        paste.builder
                .append(paste.margin())
                .append("╔").append("═".repeat(paste.width - 2)).append("╗\n")

                .append(paste.margin())
                .append("║").append(front)
                .append("▓").append("░".repeat(paste.width - front.length() - back.length() - 4)).append("▓")
                .append(back).append("║\n")

                .append(paste.margin())
                .append("╚").append("═".repeat(paste.width - 2)).append("╝\n\n");
    }


    @Override
    public void deserialize(JsonObject object) {
        section = object.get("section").getAsInt();
        subSection = object.get("sub_section").getAsInt();
        title = object.get("title").getAsString();
    }
}
