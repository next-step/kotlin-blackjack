package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 숫자와 슈트로 이루어져 있다`() {
        val suit = CardSuit.CLOVER
        val card = Card.Jack(suit)

        assertThat(card.suit).isEqualTo(suit)
    }

    @Nested
    inner class `카드 숫자가` {
        @Test
        fun `2부터 10 사이의 숫자일 경우 해당 숫자의 값으로 계산된다`() {
            val suit = CardSuit.CLOVER
            val cardToExpectedValuePairs = listOf(
                Card.Two(suit) to 2,
                Card.Three(suit) to 3,
                Card.Four(suit) to 4,
                Card.Five(suit) to 5,
                Card.Six(suit) to 6,
                Card.Seven(suit) to 7,
                Card.Eight(suit) to 8,
                Card.Nine(suit) to 9,
                Card.Ten(suit) to 10
            )

            cardToExpectedValuePairs.forEach { (card, expectedValue) ->
                assertThat(card.value).isEqualTo(expectedValue)
                assertThat(card.value + card.bonusValue).isEqualTo(expectedValue)
            }
        }

        @Test
        fun `J, Q, K 일 경우 10으로 계산된다`() {
            val suit = CardSuit.CLOVER
            val list = listOf(Card.Jack(suit), Card.Queen(suit), Card.King(suit))

            list.forEach { card ->
                assertThat(card.value).isEqualTo(10)
                assertThat(card.value + card.bonusValue).isEqualTo(10)
            }
        }

        @Test
        fun `A 일 경우 1 또는 11로 계산된다`() {
            val suit = CardSuit.CLOVER
            val aceCard = Card.Ace(suit)

            assertThat(aceCard.value).isEqualTo(1)
            assertThat(aceCard.value + aceCard.bonusValue).isEqualTo(11)
        }
    }

    @Test
    fun `카드 숫자와 슈트를 알려주는 카드 이름을 가져올 수 있다`() {
        val card = Card.Jack(CardSuit.DIAMOND)

        val expectedName = "J다이아몬드"

        assertThat(card.getName()).isEqualTo(expectedName)
    }
}
