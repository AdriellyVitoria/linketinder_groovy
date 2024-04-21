import org.example.linketinder.dao.implementacoes.CandidatoCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.implementacoes.CandidatoCompetenciaServiceImpl
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico
import org.junit.Assert
import org.junit.Test

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoCompetenciaServicoTeste {
    private CandidatoCompetenciaServico candidatoCompetenciaServico
    private CandidatoCompetenciaDao candidatoCompetenciaDaoMock

    CandidatoCompetenciaServicoTeste(){
        candidatoCompetenciaDaoMock = mock(CandidatoCompetenciaDao.class)
        candidatoCompetenciaServico = new CandidatoCompetenciaServiceImpl(candidatoCompetenciaDaoMock)
    }

    @Test
    void testeInserirCompetenciaEmCandidatoComSucesso(){
        Integer id_competencia = 2
        String cpf_teste = "123"
        when(candidatoCompetenciaDaoMock.inserir(id_competencia, cpf_teste)).thenReturn(true)

        boolean resultado = candidatoCompetenciaServico.inserir(id_competencia, cpf_teste)

        Assert.assertTrue(resultado)
        verify(candidatoCompetenciaDaoMock, times(1)).inserir(id_competencia, cpf_teste)
    }

    @Test
    void testeListarCompetenciaCandidato(){
        String cpf_teste = "123"
        ArrayList<Competencia> competencias = new ArrayList<Competencia>()
        competencias.add(new Competencia(1, 'java'))
        competencias.add(new Competencia(2, 'C#'))
        when(candidatoCompetenciaDaoMock.listarCompetencia(cpf_teste)).thenReturn(competencias)

        ArrayList<Competencia> retorno = candidatoCompetenciaServico.listarCompetencia(cpf_teste)

        Assert.assertEquals(competencias, retorno)
        verify(candidatoCompetenciaDaoMock, times(1)).listarCompetencia(cpf_teste)
    }

    @Test
    void testeDeletarCompetenciaCandidato(){
        Integer id_competencia = 2
        String cpf_teste = "123"
        when(candidatoCompetenciaDaoMock.deletar(id_competencia, cpf_teste)).thenReturn(true)

        boolean retorno = candidatoCompetenciaServico.deletar(id_competencia, cpf_teste)

        Assert.assertTrue(retorno)
        verify(candidatoCompetenciaDaoMock, times(1)).deletar(id_competencia, cpf_teste)
    }
}
