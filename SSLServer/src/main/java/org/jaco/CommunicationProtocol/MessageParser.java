package org.jaco.CommunicationProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageParser {
    private Map<String, String> fields;
    private String fastaContent;

    public MessageParser() {
        fields = new HashMap<>();
        fastaContent = "";
    }

    public void parse(BufferedReader in) throws IOException {
        String line;
        boolean fastaMode = false;
        StringBuilder fastaBuilder = new StringBuilder();

        while ((line = in.readLine()) != null) {
            if (line.equals("END")) break;

            if (line.equals("FASTA_START")) {
                fastaMode = true;
                continue;
            }
            if (line.equals("FASTA_END")) {
                fastaMode = false;
                fastaContent = fastaBuilder.toString();
                continue;
            }

            if (fastaMode) {
                fastaBuilder.append(line).append("\n");
            } else if (line.contains(": ")) {
                String[] parts = line.split(": ", 2);
                fields.put(parts[0], parts[1]);
            }
        }
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public String getFASTA() {
        return fastaContent;
    }

    public String getCommand() {
        return fields.get("COMMAND");
    }
}
