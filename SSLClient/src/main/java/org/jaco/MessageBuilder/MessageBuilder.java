package org.jaco.MessageBuilder;

// Construye mensajes en el formato acordado con el servidor
public class MessageBuilder {
    private StringBuilder builder;

    public MessageBuilder(String command) {
        builder = new StringBuilder();
        builder.append("COMMAND: ").append(command).append("\n");
    }

    // Añade un campo clave: valor como nueva línea al mensaje
    public MessageBuilder addField(String key, String value) {
        builder.append(key).append(": ").append(value).append("\n");
        return this;
    }

    // Inserta un bloque FASTA delimitado por FASTA_START y FASTA_END
    public MessageBuilder addFASTA(String fastaContent) {
        builder.append("FASTA_START\n");
        builder.append(fastaContent).append("\n");
        builder.append("FASTA_END\n");
        return this;
    }

    // Finaliza el mensaje agregando END y devuelve el texto completo
    public String build() {
        builder.append("END\n");
        return builder.toString();
    }
}
