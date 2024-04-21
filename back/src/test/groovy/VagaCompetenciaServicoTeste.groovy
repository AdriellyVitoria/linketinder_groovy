import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.implementacoes.VagaCompetenciaServiceImpl
import org.example.linketinder.service.interfaces.VagaCompetenciaService
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class VagaCompetenciaServicoTeste {

    private VagaCompetenciaDao vagaCompetenciaDaoMock
    private VagaCompetenciaService vagaCompetenciaService

    VagaCompetenciaServicoTeste() {
        vagaCompetenciaDaoMock = mock(VagaCompetenciaDao.class)
        vagaCompetenciaService = new VagaCompetenciaServiceImpl(vagaCompetenciaDaoMock)
    }

    @Test
    void testeInserirCompetenciaEmVaga() {

        Integer id_competencia = 2
        Integer id_vaga = 2
        when(vagaCompetenciaDaoMock.inserir(id_competencia, id_vaga)).thenReturn(true)

        boolean retorno = vagaCompetenciaService.inserir(id_competencia, id_vaga)

        Assert.assertTrue(retorno)
        verify(vagaCompetenciaDaoMock, times(1)).inserir(id_competencia, id_vaga)
    }

    @Test
    void testeDeletarCompetenciaVaga() {
        Integer id_competencia = 2
        Integer id_vaga = 2
        when(vagaCompetenciaDaoMock.deletar(id_competencia, id_vaga)).thenReturn(true)

        boolean retorno = vagaCompetenciaService.deletar(id_competencia, id_vaga)

        Assert.assertTrue(retorno)
        verify(vagaCompetenciaDaoMock, times(1)).deletar(id_competencia, id_vaga)
    }

    @Test
    void testeListarCompetenciaVaga() {
        Integer id_Vaga = 1
        ArrayList<Competencia> competencias = new ArrayList<Competencia>()
        competencias.add(new Competencia(1, 'java'))
        competencias.add(new Competencia(2, 'C#'))
        when(vagaCompetenciaDaoMock.listarCompetencia(id_Vaga)).thenReturn(competencias)

        ArrayList<Competencia> retorno = vagaCompetenciaService.listarCompetencia(id_Vaga)

        Assert.assertEquals(competencias, retorno)
        verify(vagaCompetenciaDaoMock, times(1)).listarCompetencia(id_Vaga)
    }
}