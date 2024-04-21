import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.implementacoes.CandidatoServiceImpl
import org.example.linketinder.service.interfaces.CandidatoService
import org.junit.Assert
import org.junit.Test
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
    void testeEntradaCandidato() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest('mendes@', '123')
        PessoaFisica pessoaFisica = criarPessoarFisica()
        ArrayList<Competencia> competencias = new ArrayList<Competencia>()
        competencias.add(new Competencia(1, 'java'))
        competencias.add(new Competencia(2, 'C#'))
        when(candidatoDaoMock.entradaCandidato(loginRequest)).thenReturn(pessoaFisica)
        when(candidatoCompetenciaDaoMock.listarCompetencia(pessoaFisica.cpf)).thenReturn(competencias)

        //Act
        PessoaFisica retorno = candidatoService.entradaCandidato(loginRequest)

        // Assert
        Assert.assertEquals(retorno, pessoaFisica)
        Assert.assertEquals(retorno.competencias, competencias)
        verify(candidatoDaoMock, times(1)).entradaCandidato(loginRequest)
        verify(candidatoCompetenciaDaoMock, times(1)).listarCompetencia(pessoaFisica.cpf)
    }

    @Test
    void testeInserirCandidato(){
        PessoaFisica pessoaFisica = criarPessoarFisica()
        when(candidatoDaoMock.inserir(pessoaFisica)).thenReturn(true)

        boolean resultado = candidatoService.inserir(pessoaFisica)

        Assert.assertTrue(resultado)
        verify(candidatoDaoMock, times(1)).inserir(pessoaFisica)
    }

    @Test
    void testeAtualizarPessoa() {
        PessoaFisica pessoaFisica = criarPessoarFisica()
        when(candidatoDaoMock.atualizar(pessoaFisica)).thenReturn(true)

        boolean retorno = candidatoService.atualizar(pessoaFisica)

        Assert.assertTrue(retorno)
        verify(candidatoDaoMock, times(1)).atualizar(pessoaFisica)
    }

    @Test
    void testeDeletarCandidato() {
        String cpf_teste = '123'
        when(candidatoDaoMock.deletar(cpf_teste)).thenReturn(true)

        boolean retorno = candidatoService.deletar(cpf_teste)

        Assert.assertTrue(retorno)
        verify(candidatoDaoMock, times(1)).deletar(cpf_teste)
    }
}
