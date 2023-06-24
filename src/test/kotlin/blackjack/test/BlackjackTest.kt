package blackjack.test

import blackjack.domain.Cards
import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun initiateCardListTest() {
        assertThat(Cards.getCards().count()).isEqualTo(52)
    }

    @Test
    fun initiatePlayerTest() {
        val player = Player("은지")
        assertThat(player.getName()).isEqualTo("은지")
    }
}
