package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class DealerTest {

    private val cards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.THREE)))

    @Test
    fun stay() {
        val dealer = Dealer(Hit(cards))

        dealer.stay()
        Assertions.assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun takeCardTest() {
        val dealer = Dealer(Hit(cards))
        val addedCard = Card(CardShape.SPADE, CardType.TWO)
        dealer.takeCard(addedCard)

        Assertions.assertThat(dealer.state.cardNames).contains(addedCard.toString())
    }

    @Test
    fun takeFirstTwoCardsTest() {
        val dealer = Dealer(NotStarted())
        val card1 = Card(CardShape.SPADE, CardType.TWO)
        val card2 = Card(CardShape.SPADE, CardType.THREE)

        dealer.takeFirstTwoCards(card1, card2)

        Assertions.assertThat(dealer.state).isInstanceOf(Hit::class.java)
        Assertions.assertThat(dealer.state.cardNames).contains(card1.toString(), card2.toString())
    }

    @Test
    fun cardPointSumTest() {
        val dealer = Dealer(Hit(cards))
        val cardPointSum = dealer.cardPointSum()

        Assertions.assertThat(cardPointSum).isEqualTo(5)
    }

    @Test
    fun profitTest() {
        val dealer = Dealer(Hit(cards))

        val playerProfits = listOf(Profit("a", BigDecimal("1000")), Profit("b", BigDecimal("2000")))
        val result = dealer.profit(playerProfits)

        Assertions.assertThat(result).isEqualTo(BigDecimal("-3000"))
    }
}
