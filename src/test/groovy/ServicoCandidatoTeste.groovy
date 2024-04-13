import org.example.linketinder.database.database.ServicoConectarBanco

import java.sql.Connection
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ServicoCandidatoTeste {
    private ServicoConectarBanco servicoConectarBancoMock

    ServicoCandidatoTeste() {
        def connectionMock = mock(Connection.class)
        servicoConectarBancoMock = mock(ServicoConectarBanco.class)
        when(servicoConectarBancoMock.conectar()).thenReturn(connectionMock)
    }
}
