package org.jaco.configserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties props = new Properties();
    private static String ruta_properties = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/SSLServer/configuration.properties";

    static {
        try (FileInputStream fis = new FileInputStream(ruta_properties)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar configuration.properties del servidor", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}
