package org.example.linketinder.modelos

class LoginRequest {
    String email
    String senha

    LoginRequest(String email, String senha) {
        this.email = email
        this.senha = senha
    }
}
