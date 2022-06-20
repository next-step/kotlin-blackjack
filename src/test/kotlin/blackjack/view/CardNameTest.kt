package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardNameTest {
    @Test
    fun `카드 이름을 가져올 수 있다`() {
        val firstCardWithName = Card.Two(CardSuit.CLOVER) to "2클로버"
        val secondCardWithName = Card.Ten(CardSuit.DIAMOND) to "10다이아몬드"
        val thirdCardWithName = Card.Queen(CardSuit.HEART) to "Q하트"
        val fourthCardWithName = Card.Ace(CardSuit.SPADE) to "A스페이드"

        val listOfCardsWithNames = listOf(firstCardWithName, secondCardWithName, thirdCardWithName, fourthCardWithName)

        listOfCardsWithNames.forEach { (card, expectedName) ->
            assertThat(CardName.of(card)).isEqualTo(expectedName)
        }
    }
}
