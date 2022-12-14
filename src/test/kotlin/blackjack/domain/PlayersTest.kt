package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `참가자가 8명을 초과하면 예외 발생`() {
        shouldThrow<IllegalArgumentException> {
            val gamePlayers = listOf(
                GamePlayer("A"), GamePlayer("B"), GamePlayer("C"),
                GamePlayer("D"), GamePlayer("E"), GamePlayer("F"),
                GamePlayer("G"), GamePlayer("H"), GamePlayer("I")
            )
            Players(gamePlayers)
        }
    }
}
