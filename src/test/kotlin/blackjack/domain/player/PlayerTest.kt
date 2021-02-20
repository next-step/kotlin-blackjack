package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun 자신의_카드들을_가지고_있다() {
        val deck = Deck.createDeck()
        val player = Player("van", 10000)
        player.addCard(deck.draw())
        player.addCard(deck.draw())
        player.addCard(deck.draw())

        val cards = player.cards
        assertThat(cards.size).isEqualTo(3)
    }

    @Test
    fun 포인트를_확인한다() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TWO,Suit.CLOVER))
        player.addCard(Card(Denomination.TEN,Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE,Suit.CLOVER))

        assertThat(player.score()).isEqualTo(17)
    }

    @Test
    fun ACE포함이면서_11로_계산이_가능할_때() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.ACE,Suit.CLOVER))
        player.addCard(Card(Denomination.TWO,Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE,Suit.CLOVER))

        assertThat(player.score()).isEqualTo(18)
    }

    @Test
    fun ACE포함이면서_11로_계산이_불가능할_때() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.ACE,Suit.CLOVER))
        player.addCard(Card(Denomination.TEN,Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE,Suit.CLOVER))

        assertThat(player.score()).isEqualTo(16)
    }

    @Test
    fun Busrt여부를_알_수_있다() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.SEVEN,Suit.CLOVER))
        player.addCard(Card(Denomination.TEN,Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE,Suit.CLOVER))

        assertThat(player.isBust()).isTrue()
    }

    @Test
    fun `자신의 수익금을 알 수 있다`() {
        val player = Player("van", 10000)

        assertThat(player.getProfit(true, 1.0)).isEqualTo(10000)
        assertThat(player.getProfit(false, 1.0)).isEqualTo(-10000)
    }
}
