package org.example.linketinder.database

import java.sql.Connection
import java.sql.DriverManager

class ConectarBanco {
    Connection conexao
    private static ConectarBanco instancia

    private ConectarBanco(){
        conexao = conectar()
    }

    static ConectarBanco criarInstancia() {
        if (instancia == null) {
            instancia = new ConectarBanco()
        }
        return instancia
    }

    private Connection conectar() {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123456");
        props.setProperty("ssl", "false");

        String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/linketinder";
        try {
            return DriverManager.getConnection(URL_SERVIDOR, props);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassNotFoundException){
                System.err.println("Verifique o driver de conexão");
            }else {
                System.err.println("Verifique se o servidor está ativo");
            };
            System.exit(-42);
            return null;
        }
    }

}
