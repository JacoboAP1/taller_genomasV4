package org.jaco.configclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties props = new Properties();
    private static String ruta_propiedades = "C:/Users/arroy/OneDrive/Documentos/6to semestre/back/taller 1 genomas/taller_genomasV4/SSLClient/configuration.properties";

    static {
        try (FileInputStream fis = new FileInputStream(ruta_propiedades)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar configuration.properties del cliente", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}
