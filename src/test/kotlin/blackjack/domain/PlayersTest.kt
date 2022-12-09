package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `참가자가 8명을 초과하면 예외 발생`() {
        shouldThrow<IllegalArgumentException> {
            val players = listOf(
                Player("A"), Player("B"), Player("C"),
                Player("D"), Player("E"), Player("F"),
                Player("G"), Player("H"), Player("I")
            )
            Players(players)
        }
    }
}
