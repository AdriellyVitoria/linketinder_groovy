package org.example.linketinder

import org.example.linketinder.servidor.Servidor

class Main {
    static void main(String[] args) {
        Servidor servidor = new Servidor()
        servidor.iniciarServidor()
    }
}
