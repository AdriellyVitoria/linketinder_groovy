import org.example.linketinder.dao.implementacoes.EmpresaDaoImpl
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.service.implementacoes.EmpresaServiceImpl
import org.example.linketinder.service.interfaces.EmpresaService
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

class EmpresaServicoTeste {

    private EmpresaDao empresaDaoMock
    private EmpresaService empresaService

    EmpresaServicoTeste(){
        empresaDaoMock = mock(EmpresaDao.class)
        empresaService = new EmpresaServiceImpl(empresaDaoMock)
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
    void testeEntradaEmpresa() {
        LoginRequest loginRequest = new LoginRequest("mendes@", '1223')
        PessoaJuridica pessoaJuridica = criarPessoarJuridica()
        when(empresaDaoMock.entradaEmpresa(loginRequest)).thenReturn(pessoaJuridica)

        PessoaJuridica retorno = empresaService.entradaEmpresa(loginRequest)

        Assert.assertEquals(pessoaJuridica, retorno)
        verify(empresaDaoMock, times(1)).entradaEmpresa(loginRequest)

    }

    @Test
    void testeInserirEmpresa() {
        PessoaJuridica empresa = criarPessoarJuridica()
        when(empresaDaoMock.inserir(empresa)).thenReturn(true)

        boolean retorno = empresaService.inserir(empresa)

        Assert.assertTrue(retorno)
        verify(empresaDaoMock, times(1)).inserir(empresa)
    }

    @Test
    void testeAtualizarEmpresa() {
        PessoaJuridica empresa = criarPessoarJuridica()
        when(empresaDaoMock.atualizar(empresa)).thenReturn(true)

        boolean retorno = empresaService.atualizar(empresa)

        Assert.assertTrue(retorno)
        verify(empresaDaoMock, times(1)).atualizar(empresa)
    }

    @Test
    void testeDeletarEmpresa() {
        String cnpj_teste = '123'
        when(empresaDaoMock.deletar(cnpj_teste)).thenReturn(true)

        boolean retorno = empresaService.deletar(cnpj_teste)

        Assert.assertTrue(retorno)
        verify(empresaDaoMock, times(1))deletar(cnpj_teste)
    }
}
