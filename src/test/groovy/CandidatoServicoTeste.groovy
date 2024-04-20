import org.example.linketinder.dao.implementacoes.CandidatoCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.CandidatoDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.implementacoes.CandidatoServiceImpl
import org.example.linketinder.service.interfaces.CandidatoService
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

class CandidatoServicoTeste {
    private CandidatoDao candidatoDaoMock
    private CandidatoCompetenciaDao candidatoCompetenciaDaoMock
    private CandidatoService candidatoService

    CandidatoServicoTeste() {
        candidatoDaoMock = mock(CandidatoDao.class)
        candidatoCompetenciaDaoMock = mock(CandidatoCompetenciaDao.class)
        candidatoService = new CandidatoServiceImpl(candidatoDaoMock, candidatoCompetenciaDaoMock)
    }

    private PessoaFisica criarPessoarFisica(){
        return new PessoaFisica(
                '123',
                'Adrielly',
                'mendes@',
                '2323',
                '2222',
                'teste',
                23,
                'PB'
        )
    }

    @Test
    void testeInserirCandidato(){
        PessoaFisica pessoaFisica = criarPessoarFisica()
        when(candidatoDaoMock.inserir(pessoaFisica)).thenReturn(true)

        boolean resultado = candidatoService.inserir(pessoaFisica)

        Assert.assertTrue(resultado)
        verify(candidatoDaoMock.inserir(pessoaFisica), times(1))
    }

    @Test
    void testeAtualizarPessoa() {
    PessoaFisica pessoaFisica = criarPessoarFisica()

    boolean retorno = candidatoDao.atualizar(pessoaFisica)

    Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarCandidato() {
        String cpf_teste = '123'

        boolean retorno = candidatoDao.deletar(cpf_teste)

        Assert.assertTrue(retorno)
    }
}
