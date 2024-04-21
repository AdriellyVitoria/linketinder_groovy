import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaDaoImpl
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.service.implementacoes.VagaServiceImpl
import org.example.linketinder.service.interfaces.VagaService
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.ArgumentMatchers.refEq
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class VagaServicoTeste {

    private VagaDao vagaDaoMock
    private VagaService vagaService
    private VagaCompetenciaDao vagaCompetenciaDaoMock

    VagaServicoTeste() {
        vagaDaoMock = mock(VagaDao.class)
        vagaCompetenciaDaoMock = mock(VagaCompetenciaDao.class)
        vagaService = new VagaServiceImpl(vagaDaoMock, vagaCompetenciaDaoMock)
    }

    private Vaga criarVaga() {
        return new Vaga(
                1,
                'descricao',
                'vaga',
                'local'
        )
    }

    @Test
    void testeCriarVaga() {
        Vaga vaga = criarVaga()

        when(vagaDaoMock.criar(vaga)).thenReturn(true)
        boolean retorno = vagaService.criar(vaga)

        Assert.assertTrue(retorno)
        verify(vagaDaoMock, times(1)).criar(vaga)
    }

    @Test
    void testeListarTodasAsVagas(){
        ArrayList<Vaga> vagas = new ArrayList<>()
        vagas.add(new Vaga(1, 'testes', 'teste', 'teste'))
        vagas.add(new Vaga(2, 'testes', 'teste', 'teste'))
        when(vagaDaoMock.listarTodas()).thenReturn(vagas)

        ArrayList<Vaga> retorno = vagaService.listarTodas()

        Assert.assertEquals(vagas, retorno)
        verify(vagaDaoMock, times(1)).listarTodas()
    }

    @Test
    void testeListarVagaEmpresa() {
        String cnpj_teste = '123'
        ArrayList<Vaga> vagas = new ArrayList<>()
        vagas.add(new Vaga(1, 'testes', 'teste', 'teste'))
        vagas.add(new Vaga(2, 'testes', 'teste', 'teste'))
        when(vagaDaoMock.listarPorCnpj(cnpj_teste)).thenReturn(vagas)

        ArrayList<Vaga> retorno = vagaService.listar(cnpj_teste)

        Assert.assertEquals(vagas, retorno)
        verify(vagaDaoMock, times(1)).listarPorCnpj(cnpj_teste)
    }

    @Test
    void testeAtualizarVaga() {
        Integer id_vaga = 1
        Vaga vaga = criarVaga()
        when(vagaDaoMock.atualizar(id_vaga, vaga)).thenReturn(true)

        boolean retorno = vagaService.atualizar(id_vaga, vaga)

        Assert.assertTrue(retorno)
        verify(vagaDaoMock, times(1)).atualizar(id_vaga, vaga)
    }

    @Test
    void testeDeletarVaga() {
        Integer id_vaga = 1
        when(vagaDaoMock.deletar(id_vaga)).thenReturn(true)

        boolean retorno = vagaService.deletar(id_vaga)

        Assert.assertTrue(retorno)
        verify(vagaDaoMock, times(1)).deletar(id_vaga)
    }
}
