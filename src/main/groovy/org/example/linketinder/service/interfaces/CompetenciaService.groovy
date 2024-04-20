package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.Competencia

interface CompetenciaService {
    boolean inserir(String competencia)
    ArrayList<Competencia> listarTodas()
}