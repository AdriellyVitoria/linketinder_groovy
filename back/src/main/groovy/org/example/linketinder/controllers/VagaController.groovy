package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.VagaService

class VagaController extends Controller {
    private  VagaService vagaService

    VagaController(VagaService vagaService) {
        this.vagaService = vagaService
    }
}
