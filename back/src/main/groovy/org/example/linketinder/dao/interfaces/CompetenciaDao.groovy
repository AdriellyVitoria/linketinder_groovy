package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.Competencia

interface CompetenciaDao {
    boolean inserir(String competencia)
    ArrayList<Competencia> listarTodas()
}