package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackCardPointCalculatorTest {
    @Test
    internal fun `카드의 점수 합계는 최대한 21을 넘지 않으면서 가장 가까운 점수로 계산된다`() {
        val pointSum = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card(CardRank.ACE, CardSuit.HEART),
                Card(CardRank.ACE, CardSuit.SPADE),
                Card(CardRank.NINE, CardSuit.HEART),
            )
        )
        pointSum shouldBe 21

        val pointSum2 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card(CardRank.TWO, CardSuit.HEART),
                Card(CardRank.TEN, CardSuit.SPADE),
            )
        )
        pointSum2 shouldBe 12

        val pointSum3 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card(CardRank.ACE, CardSuit.HEART),
                Card(CardRank.TEN, CardSuit.SPADE),
            )
        )
        pointSum3 shouldBe 21

        val pointSum4 = BlackjackCardPointCalculator.calculate(
            cards = listOf(
                Card(CardRank.ACE, CardSuit.HEART),
                Card(CardRank.TWO, CardSuit.SPADE),
                Card(CardRank.NINE, CardSuit.SPADE),
            )
        )
        pointSum4 shouldBe 12
    }
}
