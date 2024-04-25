package org.example.linketinder.controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange

abstract class Controller {
    protected Gson gson

    Controller() {
        gson = new Gson()
    }

    void handleRequest(HttpExchange request) {
        String metodo = request.getRequestMethod()

        if (metodo == "GET") {
            handleGetRequest(request)
        } else if (metodo == "POST") {
            handlePostRequest(request)
        } else if (metodo == "PUT") {
            handlePutRequest(request)
        } else if (metodo == "DELETE") {
            handleDeleteRequest(request)
        }
        else {
            sendResponse(request, 405, "Método não implementado")
        }
    }

    protected void handleGetRequest(HttpExchange request) {
        sendResponse(request, 405, "Método não implementado")
    }

    protected void handlePostRequest(HttpExchange request) {
        sendResponse(request, 405, "Método não implementado")
    }

    protected void handlePutRequest(HttpExchange request) {
        sendResponse(request, 405, "Método não implementado")
    }

    protected void handleDeleteRequest(HttpExchange request) {
        sendResponse(request, 405, "Método não implementado")
    }

    protected void sendResponse(HttpExchange request, Integer statusCode, String response) {
        try {
            request.getResponseHeaders().add("Content-Type", "application/json")
            request.sendResponseHeaders(statusCode, response ? response.getBytes().length : 0)
            OutputStream os = request.getResponseBody()
            if (response != null) {
                os.write(response.getBytes())
            }
            os.close()
        } catch (Exception e) {
            System.err.println("Erro ao enviar resposta: " + e.message)
        }
    }
}
