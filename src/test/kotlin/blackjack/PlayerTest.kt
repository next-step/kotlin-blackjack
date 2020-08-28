package blackjack

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 생성 테스트`() {
        val player = Player("a")
        assertThat(player.score()).isEqualTo(0)
        assertThat(player.name).isEqualTo("a")
    }

    @Test
    fun `플레이어 카드 드로우 테스트`() {
        val player = Player("a")
        player.draw(Card(Suit.CLUBS, Denomination.ACE))
        assertThat(player.score()).isEqualTo(11)
    }

    @Test
    fun `점수 테스트`() {
        val cards = Cards(
            listOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.NINE)
            )
        )
        val player = Player("a", cards)
        assertThat(player.score()).isEqualTo(12)
    }
}
