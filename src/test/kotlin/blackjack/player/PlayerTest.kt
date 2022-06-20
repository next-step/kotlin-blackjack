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
        assertThat(player.myCards().size).isEqualTo(0)
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player("pang")
        player.getCard(Card(Suit.SPADE, CardSymbol.ACE))

        assertThat(player.myCards()).contains(Card(Suit.SPADE, CardSymbol.ACE))
    }

    @Test
    fun `플레이어가 갖고있는 점수가 22점 이상이면 burst 상태가 된다`() {
        val player = Player("pang")
        player.getCard(Card(Suit.SPADE, CardSymbol.TEN))
        assertThat(player.burst).isEqualTo(false)

        player.getCard(Card(Suit.HEART, CardSymbol.JACK))
        assertThat(player.burst).isEqualTo(false)

        player.getCard(Card(Suit.HEART, CardSymbol.QUEEN))
        assertThat(player.burst).isEqualTo(true)
    }
}
