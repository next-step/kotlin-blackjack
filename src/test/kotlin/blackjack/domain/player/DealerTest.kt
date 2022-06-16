package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun Dealer(cards: List<Card>): Dealer {
    val dealer = Dealer()

    cards.forEach {
        dealer.addCardToHand(it)
    }

    return dealer
}

class DealerTest {
    @Test
    fun `딜러의 이름은 "딜러"이다`() {
        assertThat(Dealer().name).isEqualTo("딜러")
    }

    @Test
    fun `딜러의 카드가 16 이하일 경우 shouldDrawCard 는 true 이다`() {
        val cardsWithTotalSixteen = listOf(Card.Ten(CardSuit.CLOVER), Card.Six(CardSuit.CLOVER))

        assertThat(Dealer(cardsWithTotalSixteen).shouldDrawCard).isTrue
    }

    @Test
    fun `딜러의 카드가 17 이상일 경우 shouldDrawCard 는 false 이다`() {
        val cardsWithTotalSeventeen = listOf(Card.Ten(CardSuit.CLOVER), Card.Seven(CardSuit.CLOVER))

        assertThat(Dealer(cardsWithTotalSeventeen).shouldDrawCard).isFalse
    }
}
