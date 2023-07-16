package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackCardPointCalculatorTest {
    @Test
    internal fun `카드의 점수 합계는 최대한 21을 넘지 않으면서 가장 가까운 점수로 계산된다`() {
        val pointSum = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card.of(CardRank.ACE, CardSuit.HEART),
                Card.of(CardRank.ACE, CardSuit.SPADE),
                Card.of(CardRank.NINE, CardSuit.HEART),
            )
        )
        pointSum.toInt() shouldBe 21

        val pointSum2 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card.of(CardRank.TWO, CardSuit.HEART),
                Card.of(CardRank.TEN, CardSuit.SPADE),
            )
        )
        pointSum2.toInt() shouldBe 12

        val pointSum3 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card.of(CardRank.ACE, CardSuit.HEART),
                Card.of(CardRank.TEN, CardSuit.SPADE),
            )
        )
        pointSum3.toInt() shouldBe 21

        val pointSum4 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card.of(CardRank.ACE, CardSuit.HEART),
                Card.of(CardRank.TWO, CardSuit.SPADE),
                Card.of(CardRank.NINE, CardSuit.SPADE),
            )
        )
        pointSum4.toInt() shouldBe 12

        val pointSum5 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card.of(CardRank.JACK, CardSuit.HEART),
                Card.of(CardRank.QUEEN, CardSuit.SPADE),
                Card.of(CardRank.KING, CardSuit.SPADE),
                Card.of(CardRank.ACE, CardSuit.SPADE),
            )
        )
        pointSum5.toInt() shouldBe 31

        val pointSum6 = BlackjackCardPointCalculator.calculate(
            cards = listOf()
        )
        pointSum6.toInt() shouldBe 0
    }
}
