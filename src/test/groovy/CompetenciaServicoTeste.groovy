import org.example.linketinder.dao.implementacoes.CompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.implementacoes.CompetenciaServiceImpl
import org.example.linketinder.service.interfaces.CompetenciaService
import org.junit.Test
import org.junit.Assert

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class CompetenciaServicoTeste {
    private CompetenciaDao competenciaDaoMck
    private CompetenciaService competenciaService

    CompetenciaServicoTeste() {
        competenciaDaoMck = mock(CompetenciaDao.class)
        competenciaService = new CompetenciaServiceImpl(competenciaDaoMck)
    }

    @Test
    void testeInserirCompeteciaComSucesso() {
        String competencia = 'Java'
        when(competenciaDaoMck.inserir(competencia)).thenReturn(true)

        boolean retorno = competenciaService.inserir(competencia)

        Assert.assertTrue(retorno)
        verify(competenciaDaoMck, times(1)).inserir(competencia)
    }

    @Test
    void testeListarTodasCompetencias() {
        ArrayList<Competencia> competencias = new ArrayList<Competencia>()
        competencias.add(new Competencia(1, 'java'))
        competencias.add(new Competencia(2, 'C#'))

        when(competenciaDaoMck.listarTodas()).thenReturn(competencias)

        ArrayList<Competencia> retorno = competenciaService.listarTodas()

        Assert.assertEquals(competencias, retorno)
        verify(competenciaDaoMck, times(1)).listarTodas()
    }
}
