package blackjack.domain

import blackjack.domain.card.CardScore
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerScoreTest : BehaviorSpec({
    given("점수가 10점일 때") {
        val playerScore = PlayerScore(10)

        `when`("(primary=11, secondary=10)를 더한 결과는") {
            val result = playerScore.add(CardScore(11, 10))

            then("21이다.") {
                result shouldBe PlayerScore(21)
            }
        }

        `when`("(primary=13, secondary=12)를 더한 결과는") {
            val result = playerScore.add(CardScore(13, 12))

            then("22이다.") {
                result shouldBe PlayerScore(22)
            }
        }

        `when`("(primary=11, secondary=10), (primary=11, secondary=10)를 더한 결과는") {
            val cardScores = List(2) { CardScore(11, 10) }
            val result = playerScore.add(cardScores)

            then("31이다.") {
                result shouldBe PlayerScore(31)
            }
        }

        `when`("(primary=6, secondary=6), (primary=5, secondary=5)를 더한 결과는") {
            val cardScores = listOf(CardScore(6, 6), CardScore(5, 5))
            val result = playerScore.add(cardScores)

            then("21이다.") {
                result shouldBe PlayerScore(21)
            }
        }

        `when`("(primary=11, secondary=10), (primary=5, secondary=5)를 더한 결과는") {
            val cardScores = listOf(CardScore(11, 10), CardScore(5, 5))
            val result = playerScore.add(cardScores)

            then("25이다.") {
                result shouldBe PlayerScore(25)
            }
        }

        `when`("(primary=11, secondary=10), (primary=11, secondary=10),  (primary=5, secondary=5), (primary=3, secondary=3)를 더한 결과는") {
            val cardScores = listOf(CardScore(11, 10), CardScore(11, 10), CardScore(5, 5), CardScore(3, 3))
            val result = playerScore.add(cardScores)

            then("38이다.") {
                result shouldBe PlayerScore(38)
            }
        }
    }
})
