package blackjack.domain

import blackjack.domain.rule.DefaultScoringRule
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScoringRuleTest {

    @ParameterizedTest
    @MethodSource("casesOfSumAllExceptAce")
    fun `카드 리스트를 받으면, 총 점수를 계산한다 (ACE 제외)`(cardsToExpected: Pair<List<Card>, Int>) {
        val scoringRule = DefaultScoringRule()

        scoringRule.sumAll(cardsToExpected.first) shouldBe cardsToExpected.second
    }

    @ParameterizedTest
    @MethodSource("casesOfSumAllUsingAceTo11")
    fun `ACE 카드가 11로 계산되는 경우`(cardsToExpected: Pair<List<Card>, Int>) {
        val scoringRule = DefaultScoringRule()

        scoringRule.sumAll(cardsToExpected.first) shouldBe cardsToExpected.second
    }

    @ParameterizedTest
    @MethodSource("casesOfSumAllUsingAceTo11")
    fun `ACE 카드가 1로 계산되는 경우`(cardsToExpected: Pair<List<Card>, Int>) {
        val scoringRule = DefaultScoringRule()

        scoringRule.sumAll(cardsToExpected.first) shouldBe cardsToExpected.second
    }

    companion object {
        @JvmStatic
        fun casesOfSumAllExceptAce(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        Card(CardCharacter.TWO, CardShape.SPADE),
                        Card(CardCharacter.SEVEN, CardShape.SPADE),
                    ) to 9,
                    listOf(
                        Card(CardCharacter.TWO, CardShape.SPADE),
                        Card(CardCharacter.THREE, CardShape.SPADE),
                        Card(CardCharacter.TEN, CardShape.SPADE),
                    ) to 15,
                    listOf(
                        Card(CardCharacter.TWO, CardShape.SPADE),
                        Card(CardCharacter.NINE, CardShape.SPADE),
                        Card(CardCharacter.TEN, CardShape.SPADE),
                    ) to 21,
                    listOf(
                        Card(CardCharacter.FIVE, CardShape.SPADE),
                        Card(CardCharacter.NINE, CardShape.SPADE),
                        Card(CardCharacter.TEN, CardShape.SPADE),
                    ) to 24,
                )
            )
        }

        @JvmStatic
        fun casesOfSumAllUsingAceTo11(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        Card(CardCharacter.ACE, CardShape.SPADE),
                        Card(CardCharacter.SEVEN, CardShape.SPADE),
                    ) to 18,
                    listOf(
                        Card(CardCharacter.SEVEN, CardShape.SPADE),
                        Card(CardCharacter.ACE, CardShape.SPADE),
                    ) to 18,
                    listOf(
                        Card(CardCharacter.ACE, CardShape.HEART),
                        Card(CardCharacter.TWO, CardShape.SPADE),
                        Card(CardCharacter.THREE, CardShape.SPADE),
                    ) to 16,
                    listOf(
                        Card(CardCharacter.TWO, CardShape.SPADE),
                        Card(CardCharacter.ACE, CardShape.HEART),
                        Card(CardCharacter.THREE, CardShape.SPADE),
                        Card(CardCharacter.FOUR, CardShape.SPADE),
                    ) to 20,
                )
            )
        }

        @JvmStatic
        fun casesOfSumAllUsingAceTo1(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        Card(CardCharacter.ACE, CardShape.SPADE),
                        Card(CardCharacter.SEVEN, CardShape.SPADE),
                        Card(CardCharacter.TEN, CardShape.SPADE),
                    ) to 18,
                    listOf(
                        Card(CardCharacter.EIGHT, CardShape.SPADE),
                        Card(CardCharacter.ACE, CardShape.SPADE),
                        Card(CardCharacter.NINE, CardShape.SPADE),
                    ) to 18,
                    listOf(
                        Card(CardCharacter.ACE, CardShape.HEART),
                        Card(CardCharacter.JACK, CardShape.SPADE),
                        Card(CardCharacter.TEN, CardShape.SPADE),
                    ) to 21,
                    listOf(
                        Card(CardCharacter.ACE, CardShape.HEART),
                        Card(CardCharacter.JACK, CardShape.SPADE),
                        Card(CardCharacter.QUEEN, CardShape.SPADE),
                    ) to 21,
                    listOf(
                        Card(CardCharacter.JACK, CardShape.SPADE),
                        Card(CardCharacter.ACE, CardShape.HEART),
                        Card(CardCharacter.NINE, CardShape.SPADE),
                    ) to 20,
                )
            )
        }
    }

    @Test
    fun `총 점수가 21점을 넘으면 한계 점수를 넘었다고 판단`() {
        val scoringRule = DefaultScoringRule()

        for (score in 1..21) {
            scoringRule.isOverThreshold(score) shouldBe false
        }

        for (score in 22..30) {
            scoringRule.isOverThreshold(score) shouldBe true
        }
    }
}
