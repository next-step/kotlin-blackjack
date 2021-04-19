package blackjack.domain

import blackjack.domain.card.CardType
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.running.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    private val cards = cards(CardType.TWO, CardType.THREE)

    @Test
    fun stay() {
        val dealer = Dealer(Hit(cards))

        dealer.stay()
        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun takeCardTest() {
        val dealer = Dealer(Hit(cards))
        dealer.takeCard(cardTwo)

        assertThat(dealer.state.cardNames).contains(cardTwo.toString())
    }

    @Test
    fun takeFirstTwoCardsTest() {
        val dealer = Dealer(NotStarted())

        dealer.takeFirstTwoCards(cardTwo, cardThree)

        assertThat(dealer.state).isInstanceOf(Hit::class.java)
        assertThat(dealer.state.cardNames).contains(cardTwo.toString(), cardThree.toString())
    }

    @Test
    fun cardPointSumTest() {
        val dealer = Dealer(Hit(cards))
        val cardPointSum = dealer.cardPointSum()

        assertThat(cardPointSum).isEqualTo(5)
    }
}
