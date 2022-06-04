package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `a player show all of card`() {
        val name = "qyu"
        val cards = listOf(
            Card(Type.SPADE, Value.KING),
            Card(Type.HEART, Value.NINE)
        )

        val player = Player(name, cards)

        assertThat(player.toString())
            .contains("KING of SPADE", "NINE of HEART")
    }
}
