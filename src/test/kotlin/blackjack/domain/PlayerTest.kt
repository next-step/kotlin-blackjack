package blackjack.domain

import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.Running.Hit
import blackjack.domain.state.started.finished.BlackJack
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class PlayerTest {

    private val cards = cards(CardType.TWO, CardType.THREE)

    @Test
    fun stay() {
        val player = Player("song", Hit(cards))

        player.stay()
        assertThat(player.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun takeCardTest() {
        val player = Player("song", Hit(cards))
        player.takeCard(cardTwo)

        assertThat(player.state.cardNames).contains(cardTwo.toString())
    }

    @Test
    fun `takeFirstTwoCards 블랙잭이 아닌 경우`() {
        val player = Player("song")

        player.takeFirstTwoCards(cardTwo, cardThree)

        assertThat(player.state).isInstanceOf(Hit::class.java)
        assertThat(player.state.cardNames).contains(cardTwo.toString(), cardThree.toString())
    }

    @Test
    fun `takeFirstTwoCards 블랙잭인 경우`() {
        val player = Player("song")

        player.takeFirstTwoCards(cardAce, cardQueen)

        assertThat(player.state).isInstanceOf(BlackJack::class.java)
        assertThat(player.state.cardNames).contains(cardAce.toString(), cardQueen.toString())
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

        val dealerCards = cards(CardType.NINE, CardType.JACK)
        val dealerState = Stay(dealerCards)

        val result = player.profit(dealerState)
        assertThat(result.name).isEqualTo(name)
        assertThat(result.amount).isEqualTo(BigDecimal("-1000"))
    }
}
