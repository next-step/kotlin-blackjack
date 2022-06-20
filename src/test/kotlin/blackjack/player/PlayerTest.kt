package blackjack.player

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름을 갖는다`() {
        val player = Player("pang")
        assertThat(player.name).isEqualTo("pang")
    }

    @Test
    fun `플레이어 최초 카드는 0개다 갖는다`() {
        val player = Player("pang")
        assertThat(player.show().size).isEqualTo(0)
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player("pang")
        player.getCard(Card(Suit.SPADE, CardSymbol.ACE))

        assertThat(player.show()).contains(Card(Suit.SPADE, CardSymbol.ACE))
    }
}
