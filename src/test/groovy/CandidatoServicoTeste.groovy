import org.example.linketinder.database.database.ConectarBancoServico

import java.sql.Connection
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ServicoCandidatoTeste {
    private ConectarBancoServico servicoConectarBancoMock

    ServicoCandidatoTeste() {
        def connectionMock = mock(Connection.class)
        servicoConectarBancoMock = mock(ConectarBancoServico.class)
        when(servicoConectarBancoMock.conectar()).thenReturn(connectionMock)
    }
}
