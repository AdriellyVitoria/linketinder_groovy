import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.servicos.CandidatoCompetenciaServico
import org.example.linketinder.servicos.CandidatoServico
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CandidatoServicoTeste {
    private CandidatoDao candidatoDaoImpl

    CandidatoServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)
        CandidatoCompetenciaServico candidatoCompetenciaServico = new CandidatoCompetenciaServico(servicoConectarBancoMock)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        candidatoServico = new CandidatoServico(candidatoCompetenciaServico, servicoConectarBancoMock)
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

        boolean retorno = candidatoServico.inserir(pessoaFisica)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeAtualizarPessoa() {
    PessoaFisica pessoaFisica = criarPessoarFisica()

    boolean retorno = candidatoServico.atualizar(pessoaFisica)

    Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarCandidato() {
        String cpf_teste = '123'

        boolean retorno = candidatoServico.deletar(cpf_teste)

        Assert.assertTrue(retorno)
    }
}
