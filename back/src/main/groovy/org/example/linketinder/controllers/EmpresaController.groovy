package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.EmpresaService

class EmpresaController extends Controller {
    private EmpresaService empresaService

    EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService
    }
}
