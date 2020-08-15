package blackjack

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 생성 테스트`() {
        val player = Player("a")
        assertThat(player.cards.score() == 0 && player.name == "a").isEqualTo(true)
    }

    @Test
    fun `플레이어 카드 드로우 테스트`() {
        val player = Player("a")
        assertThat(player.cards.score() == 0 && player.name == "a").isEqualTo(true)
        player.draw(Card(Suit.CLUBS, Denomination.ACE))
        assertThat(player.cards.score() == 11).isEqualTo(true)
    }
}
