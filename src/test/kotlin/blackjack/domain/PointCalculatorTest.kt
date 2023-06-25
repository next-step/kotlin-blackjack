package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class PointCalculatorTest : BehaviorSpec({

    given("${PointCalculator.BLACKJACK_LIMIT}점을 넘지않는 덱이 있다") {
        val deck = Deck(
            LinkedList(
                listOf(
                    Card(Suit.SPADE, AceCardNumber(1)), // 이때는 11
                    Card(Suit.SPADE, NumberCardNumber(9)),
                    Card(Suit.HEART, AceCardNumber(1)), // 이때는 1
                ),
            ),
        )
        `when`("해당덱의 점수를 구하면") {
            then("합이 반환된다") {
                PointCalculator.calculatePoint(deck) shouldBe 21
            }
        }
    }

    given("${PointCalculator.BLACKJACK_LIMIT}점을 넘는 덱이 있다") {
        val deck = Deck(
            LinkedList(
                listOf(
                    Card(Suit.SPADE, JackQueenKingCardNumber(11)),
                    Card(Suit.SPADE, JackQueenKingCardNumber(12)),
                    Card(Suit.SPADE, JackQueenKingCardNumber(13)),
                ),
            ),
        )
        `when`("해당덱의 점수를 구하면") {
            then("null이 반환된다") {
                PointCalculator.calculatePoint(deck) shouldBe null
            }
        }
    }
})
