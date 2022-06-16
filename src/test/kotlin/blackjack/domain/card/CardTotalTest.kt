package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

fun `20 point card`() = listOf(
    Card.King(CardSuit.CLOVER),
    Card.Ten(CardSuit.HEART)
)

fun `blackjack card`() = listOf(
    Card.King(CardSuit.CLOVER),
    Card.Ace(CardSuit.HEART)
)

class CardTotalTest {
    @Test
    fun `카드 숫자의 합을 계산할 수 있다`() {
        assertThat(CardTotal(`20 point card`()).value).isEqualTo(20)
    }

    @Test
    fun `카드 숫자의 합이 21 보다 큰지 아닌지 판단할 수 있다`() {
        assertAll(
            {
                val cardTotalAboveTwentyOne = CardTotal(
                    `20 point card`() + Card.Two(CardSuit.CLOVER)
                )

                assertThat(cardTotalAboveTwentyOne.value).isEqualTo(22)
                assertThat(cardTotalAboveTwentyOne.isBusted).isTrue
            },
            {
                val cardTotalBelowTwentyOne = CardTotal(
                    `20 point card`()
                )

                assertThat(cardTotalBelowTwentyOne.value).isEqualTo(20)
                assertThat(cardTotalBelowTwentyOne.isBusted).isFalse
            },
            {
                val cardTotalEqualsToTwentyOne = CardTotal(
                    `20 point card`() + Card.Ace(CardSuit.CLOVER)
                )

                assertThat(cardTotalEqualsToTwentyOne.value).isEqualTo(21)
                assertThat(cardTotalEqualsToTwentyOne.isBusted).isFalse
            }
        )
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

                assertThat(cardTotalWithAceValuedAsOne.value).isEqualTo(12)
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

                assertThat(cardTotalWithAceValuedAsEleven.value).isEqualTo(21)
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

        assertThat(cardTotalAboveTwentyOne.value).isEqualTo(24)
    }
}
