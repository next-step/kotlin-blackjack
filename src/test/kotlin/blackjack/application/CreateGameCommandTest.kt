package blackjack.application

import blackjack.domain.StubDeck
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class CreateGameCommandTest {
    @Test
    fun `이름과 베팅의 숫자가 같아야 한다`() {
        assertThrows<IllegalArgumentException> {
            CreateGameCommand(
                listOf("a", "b"),
                listOf(BigDecimal.ONE),
                StubDeck.from(),
            )
        }
    }
}
