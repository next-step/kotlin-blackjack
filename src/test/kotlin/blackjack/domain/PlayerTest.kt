package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `drawCard() 패가 한장 늘어난다`() {
        val player = Player("robin")
        player.drawCard(Deck(Card.PACK))

        assertThat(player.hands.size).isEqualTo(1)
    }
}
