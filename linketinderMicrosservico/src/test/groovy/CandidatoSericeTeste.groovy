import org.example.linketindermicrosservico.dao.interfaces.CandidatoDao
import org.example.linketindermicrosservico.modelos.PessoaFisica
import org.example.linketindermicrosservico.services.implemetacoes.CandidatoServiceImpl
import org.example.linketindermicrosservico.services.interfaces.CandidatoService
import org.junit.jupiter.api.Test

class CandidatoSericeTeste {
    private CandidatoDao candidatoDaoMock
    private CandidatoService candidatoService

    CandidatoServicoTeste() {
        candidatoDaoMock = mock(CandidatoDao.class)
        candidatoService = new CandidatoServiceImpl(candidatoDaoMock)
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

    @org.junit.Test
    void testeInserirCandidato(){
        PessoaFisica pessoaFisica = criarPessoarFisica()
        when(candidatoDaoMock.inserir(pessoaFisica)).thenReturn(true)

        boolean resultado = candidatoService.inserir(pessoaFisica)

        Assert.assertTrue(resultado)
        verify(candidatoDaoMock, times(1)).inserir(pessoaFisica)
    }
}
