package blackjack.domain

import blackjack.domain.card.CardPack
import blackjack.domain.card.CardType
import blackjack.domain.card.SameCardFactory
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.finished.Bust
import blackjack.domain.state.started.run.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    private val cards = cards(CardType.TWO, CardType.THREE)

    @Test
    fun `dealer의 카드가 16이하면 카드를 더 받는다`() {
        val dealer = Dealer(Hit(cards(CardType.TWO, CardType.THREE)))

        dealer.takeCardUntilSixteen(CardPack())
        assertThat(dealer.state.cardPointSum()).isGreaterThan(16)
    }

    @Test
    fun `dealer의 16이상이 될 때까지 카드를 더 받은 결과가 21보다 작다`() {
        val dealer = Dealer(Hit(cards(CardType.TWO, CardType.THREE)))

        dealer.takeCardUntilSixteen(CardPack(SameCardFactory(CardType.FIVE)))
        assertThat(dealer.state.cardPointSum()).isEqualTo(20)
        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `dealer의 16이상이 될 때까지 카드를 더 받은 결과가 21보다 크다`() {
        val dealer = Dealer(Hit(cards(CardType.TWO, CardType.THREE)))

        dealer.takeCardUntilSixteen(CardPack(SameCardFactory(CardType.NINE)))
        assertThat(dealer.state.cardPointSum()).isEqualTo(23)
        assertThat(dealer.state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `dealer의 카드가 16초과면 카드를 더 받지 않는다`() {
        val dealer = Dealer(Hit(cards(CardType.JACK, CardType.QUEEN)))

        dealer.takeCardUntilSixteen(CardPack())
        assertThat(dealer.cardSize).isEqualTo(2) // 2장 이상을 갖는다
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
