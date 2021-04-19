package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.state.started.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class PlayerTest {

    private val cards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.THREE)))

    @Test
    fun stay() {
        val player = Player("song", Hit(cards))

        player.stay()
        assertThat(player.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun takeCardTest() {
        val player = Player("song", Hit(cards))
        val addedCard = Card(CardShape.SPADE, CardType.TWO)
        player.takeCard(addedCard)

        assertThat(player.state.cardNames).contains(addedCard.toString())
    }

    @Test
    fun takeFirstTwoCardsTest() {
        val player = Player("song")
        val card1 = Card(CardShape.SPADE, CardType.TWO)
        val card2 = Card(CardShape.SPADE, CardType.THREE)

        player.takeFirstTwoCards(card1, card2)

        assertThat(player.state).isInstanceOf(Hit::class.java)
        assertThat(player.state.cardNames).contains(card1.toString(), card2.toString())
    }

    @Test
    fun cardPointSumTest() {
        val player = Player("song", Hit(cards))
        val cardPointSum = player.cardPointSum()

        assertThat(cardPointSum).isEqualTo(5)
    }

    @Test
    fun profitTest() {
        val name = "song"
        val player = Player(name, Stay(cards), 1000)

        val dealerCards = Cards(listOf(Card(CardShape.SPADE, CardType.NINE), Card(CardShape.SPADE, CardType.JACK)))
        val dealerState = Stay(dealerCards)

        val result = player.profit(dealerState)
        assertThat(result.name).isEqualTo(name)
        assertThat(result.amount).isEqualTo(BigDecimal("-1000"))
    }
}
