import org.example.linketinder.dao.implementacoes.CandidatoVagaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.service.implementacoes.CandidatoVagaServiceImpl
import org.example.linketinder.service.interfaces.CandidatoVagaService
import org.junit.Assert
import org.junit.Test

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CandidatoVagaServicoTeste {
    private CandidatoVagaService candidatoVagaService
    private CandidatoVagaDao candidatoVagaDaoMock
    private VagaCompetenciaDao vagaCompetenciaDaoMock

    CandidatoVagaServicoTeste(){
        candidatoVagaDaoMock = mock(CandidatoVagaDao.class)
        vagaCompetenciaDaoMock = mock(VagaCompetenciaDao.class)
        candidatoVagaService = new CandidatoVagaServiceImpl(candidatoVagaDaoMock, vagaCompetenciaDaoMock)
    }

    @Test
    void testeInserirCandidatoVagaComSucesso(){
        // Arrange
        Integer id_vaga = 2
        String cpfTeste = '123'
        when(candidatoVagaDaoMock.aplicar(id_vaga, cpfTeste)).thenReturn(true)
        // Act
        boolean retorno = candidatoVagaService.aplicar(id_vaga, cpfTeste)
        // Assert
        Assert.assertTrue(retorno)
        verify(candidatoVagaDaoMock, times(1)).aplicar(id_vaga, cpfTeste)
    }

    @Test
    void testeListarVagaAplicadas(){
        // Arrange
        String cpfTeste = '123'

        ArrayList<Vaga> vagas = new ArrayList<>()
        vagas.add(new Vaga(1, 'descricao', 'titulo', 'local'))
        vagas.add(new Vaga(2, 'descricao', 'titulo', 'local'))

        ArrayList<Competencia> competencias = new ArrayList<Competencia>()
        competencias.add(new Competencia(1, 'java'))
        competencias.add(new Competencia(2, 'C#'))

        when(candidatoVagaDaoMock.listarPorCpf(cpfTeste)).thenReturn(vagas)
        when(vagaCompetenciaDaoMock.listarCompetencia(anyInt())).thenReturn(competencias)
        // Act
        ArrayList<Vaga> retorno = candidatoVagaService.listarPorCpf(cpfTeste)
        // Assert
        Assert.assertEquals(vagas, retorno)
        verify(candidatoVagaDaoMock, times(1)).listarPorCpf(cpfTeste)
        verify(vagaCompetenciaDaoMock, times(2)).listarCompetencia(anyInt())
    }
}
