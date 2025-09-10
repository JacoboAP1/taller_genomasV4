package org.jaco.MessageBuilder;

// Construye mensajes en el formato acordado con el servidor
public class MessageBuilder {
    private StringBuilder builder;

    public MessageBuilder(String command) {
        builder = new StringBuilder();
        builder.append("COMMAND: ").append(command).append("\n");
    }

    public MessageBuilder addField(String key, String value) {
        builder.append(key).append(": ").append(value).append("\n");
        return this;
    }

    public MessageBuilder addFASTA(String fastaContent) {
        builder.append("FASTA_START\n");
        builder.append(fastaContent).append("\n");
        builder.append("FASTA_END\n");
        return this;
    }

    public String build() {
        builder.append("END\n");
        return builder.toString();
    }
}
