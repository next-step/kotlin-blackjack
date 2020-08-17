package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `hitCard() 패가 한장 늘어난다`() {
        val player = Player("robin")
        player hit Card.CLUB_ACE

        assertThat(player.hands).isEqualTo(Hands(listOf(Card.CLUB_ACE)))
    }
}
