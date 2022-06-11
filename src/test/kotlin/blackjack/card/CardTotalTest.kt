package blackjack.card

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardTotal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardTotalTest {
    @Test
    fun `카드 숫자의 합을 계산할 수 있다`() {
        val suit = CardSuit.CLOVER

        val cardTotal = CardTotal(
            listOf(
                Card.Two(suit),
                Card.Five(suit),
                Card.King(suit)
            )
        )

        val expectedTotal = 2 + 5 + 10

        assertThat(cardTotal.value).isEqualTo(expectedTotal)
    }

    @Test
    fun `카드 숫자의 합이 21 보다 큰지 아닌지 판단할 수 있다`() {
        assertAll(
            {
                val suit = CardSuit.CLOVER

                val cardTotalAboveTwentyOne = CardTotal(
                    listOf(
                        Card.Two(suit),
                        Card.Ten(suit),
                        Card.King(suit)
                    )
                )

                val expectedTotal = 2 + 10 + 10

                assertThat(cardTotalAboveTwentyOne.value).isEqualTo(expectedTotal)
                assertThat(cardTotalAboveTwentyOne.isAboveTwentyOne).isTrue
            },
            {
                val suit = CardSuit.CLOVER
                val cardTotalBelowTwentyOne = CardTotal(
                    listOf(
                        Card.Two(suit),
                        Card.Three(suit),
                        Card.King(suit)
                    )
                )

                val expectedTotal = 2 + 3 + 10

                assertThat(cardTotalBelowTwentyOne.value).isEqualTo(expectedTotal)
                assertThat(cardTotalBelowTwentyOne.isAboveTwentyOne).isFalse
            },
            {
                val suit = CardSuit.CLOVER
                val cardTotalEqualsToTwentyOne = CardTotal(
                    listOf(
                        Card.Two(suit),
                        Card.Nine(suit),
                        Card.King(suit)
                    )
                )

                val expectedTotal = 2 + 9 + 10

                assertThat(cardTotalEqualsToTwentyOne.value).isEqualTo(expectedTotal)
                assertThat(cardTotalEqualsToTwentyOne.isAboveTwentyOne).isFalse
            }
        )
    }

    @Test
    fun `카드 숫자의 합이 21일 경우 블랙잭이라고 판단한다`() {
        val suit = CardSuit.CLOVER

        val cardTotalEqualsToTwentyOne = CardTotal(
            listOf(
                Card.Two(suit),
                Card.Nine(suit),
                Card.King(suit)
            )
        )

        val expectedTotal = 2 + 9 + 10

        assertThat(cardTotalEqualsToTwentyOne.value).isEqualTo(expectedTotal)
        assertThat(cardTotalEqualsToTwentyOne.isBlackjack).isTrue
    }

    @Test
    fun `카드 숫자의 합이 21이 아닐 경우 블랙잭이 아니라고 판단한다`() {
        val suit = CardSuit.CLOVER

        val cardTotalNotEqualsToTwentyOne = CardTotal(
            listOf(
                Card.Ten(suit),
                Card.King(suit)
            )
        )

        val expectedTotal = 10 + 10

        assertThat(cardTotalNotEqualsToTwentyOne.value).isEqualTo(expectedTotal)
        assertThat(cardTotalNotEqualsToTwentyOne.isBlackjack).isFalse
    }

    @Test
    fun `카드에 Ace 가 있을 경우 21 보다 낮은 제일 높은 합을 반환한다`() {
        assertAll(
            {
                val suit = CardSuit.CLOVER

                val cardTotalWithAceValuedAsOne = CardTotal(
                    listOf(
                        Card.Ace(suit),
                        Card.Eight(suit),
                        Card.Three(suit)
                    )
                )

                val expectedTotal = 1 + 8 + 3

                assertThat(cardTotalWithAceValuedAsOne.value).isEqualTo(expectedTotal)
            },
            {
                val suit = CardSuit.CLOVER

                val cardTotalWithAceValuedAsEleven = CardTotal(
                    listOf(
                        Card.Ace(suit),
                        Card.Eight(suit),
                        Card.Two(suit)
                    )
                )

                val expectedTotal = 11 + 8 + 2

                assertThat(cardTotalWithAceValuedAsEleven.value).isEqualTo(expectedTotal)
            }
        )
    }

    @Test
    fun `카드에 Ace 가 있고 총 합이 21이 넘을 경우 21 보다 높은 제일 낮은 합을 반환한다`() {
        val suit = CardSuit.CLOVER

        val cardTotalAboveTwentyOne = CardTotal(
            listOf(
                Card.Ace(suit),
                Card.King(suit),
                Card.Eight(suit),
                Card.Five(suit)
            )
        )

        val expectedTotal = 1 + 10 + 8 + 5

        assertThat(cardTotalAboveTwentyOne.value).isEqualTo(expectedTotal)
    }
}
