import org.example.linketinder.dao.implementacoes.EmpresaDaoImpl
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaJuridica
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class EmpresaServicoTeste {

    private EmpresaDao empresaDao

    EmpresaServicoTeste(){
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        empresaDao = new EmpresaDaoImpl(servicoConectarBancoMock)
    }

    private PessoaJuridica criarPessoarJuridica(){
       return new PessoaJuridica(
                '123',
                'empresa',
                'empresa@teste',
                '123',
                '999',
                '233',
                'pb',
                'Brasil',
        )
    }

    @Test
    void testeInserirEmpresa() {
        PessoaJuridica empresa = criarPessoarJuridica()

        boolean retorno = empresaDao.inserir(empresa)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeAtualizarEmpresa() {
        PessoaJuridica empresa = criarPessoarJuridica()

        boolean retorno = empresaDao.atualizar(empresa)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarEmpresa() {
        String cnpj_teste = '123'

        boolean retorno = empresaDao.deletar(cnpj_teste)

        Assert.assertNotNull(retorno)
    }
}
