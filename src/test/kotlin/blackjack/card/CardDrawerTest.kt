package blackjack.card

import blackjack.domain.card.Card
import blackjack.domain.card.CardDrawer
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDrawerTest {
    @Test
    fun `카드를 한장씩 뽑을 수 있다`() {
        val firstCard = Card.Two(CardSuit.CLOVER)
        val secondCard = Card.Two(CardSuit.DIAMOND)
        val thirdCard = Card.Two(CardSuit.HEART)
        val fourthCard = Card.Two(CardSuit.SPADE)
        val listOfCards = listOf(firstCard, secondCard, thirdCard, fourthCard)

        val cardDrawer = CardDrawer(listOfCards)

        assertThat(cardDrawer.draw()).isSameAs(firstCard)
        assertThat(cardDrawer.draw()).isSameAs(secondCard)
        assertThat(cardDrawer.draw()).isSameAs(thirdCard)
        assertThat(cardDrawer.draw()).isSameAs(fourthCard)
    }

    @Test
    fun `카드를 다 뽑은 경우 null을 반환한다`() {
        val firstCard = Card.Two(CardSuit.CLOVER)
        val secondCard = Card.Two(CardSuit.DIAMOND)
        val thirdCard = Card.Two(CardSuit.HEART)
        val fourthCard = Card.Two(CardSuit.SPADE)
        val listOfCards = listOf(firstCard, secondCard, thirdCard, fourthCard)

        val cardDrawer = CardDrawer(listOfCards)

        repeat(4) {
            assertThat(cardDrawer.draw()).isNotNull
        }

        assertThat(cardDrawer.draw()).isNull()
    }
}
