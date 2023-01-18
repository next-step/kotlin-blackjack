package blackjack.domain.algorithm

import domain.algorithm.DefaultScoreOptimizationAlgorithm
import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultScoreOptimizationAlgorithmTest : BehaviorSpec({
    given("targetScore 21이면") {
        val targetScore = 21

        `when`("ACE 카드가 3장일 때") {
            val cardA = Card(CardShape.HEART, CardNumber.ACE)
            val cardB = Card(CardShape.CLOVER, CardNumber.ACE)
            val cardC = Card(CardShape.DIAMOND, CardNumber.ACE)
            val cards = listOf(cardA, cardB, cardC)

            then("최적화 된 값은 13이다.") {
                val optimizeScore = DefaultScoreOptimizationAlgorithm.optimizeScore(cards, targetScore)
                optimizeScore shouldBe 13
            }
        }

        `when`("ACE 카드가 2장일 때") {
            val cardA = Card(CardShape.HEART, CardNumber.ACE)
            val cardB = Card(CardShape.CLOVER, CardNumber.ACE)
            val cards = listOf(cardA, cardB)

            then("최적화 된 값은 12이다.") {
                val optimizeScore = DefaultScoreOptimizationAlgorithm.optimizeScore(cards, targetScore)
                optimizeScore shouldBe 12
            }
        }

        `when`("JACK 카드가 1장 ACE 카드가 1장일 때") {
            val cardA = Card(CardShape.HEART, CardNumber.JACK)
            val cardB = Card(CardShape.CLOVER, CardNumber.ACE)
            val cards = listOf(cardA, cardB)

            then("최적화 된 값은 21이다.") {
                val optimizeScore = DefaultScoreOptimizationAlgorithm.optimizeScore(cards, targetScore)
                optimizeScore shouldBe 21
            }
        }

        `when`("9 카드가 2장 ACE 카드가 2장일 때") {
            val cardA = Card(CardShape.DIAMOND, CardNumber.NINE)
            val cardB = Card(CardShape.CLOVER, CardNumber.NINE)
            val cardC = Card(CardShape.HEART, CardNumber.ACE)
            val cardD = Card(CardShape.CLOVER, CardNumber.ACE)
            val cards = listOf(cardA, cardB, cardC, cardD)

            then("최적화 된 값은 20이다.") {
                val optimizeScore = DefaultScoreOptimizationAlgorithm.optimizeScore(cards, targetScore)
                optimizeScore shouldBe 20
            }
        }
    }
})
