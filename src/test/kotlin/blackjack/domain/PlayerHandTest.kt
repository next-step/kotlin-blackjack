package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.test.FakeGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerHandTest : BehaviorSpec({
    given("카드가 TEN, THREE가 있을 때") {
        val playerHand = PlayerHand.init
            .add(
                listOf(
                    FakeGenerator.card(CardNumber.TEN),
                    FakeGenerator.card(CardNumber.THREE)
                )
            )

        `when`("ACE를 추가하면") {
            val result = playerHand.add(FakeGenerator.card(CardNumber.ACE))

            then("점수는 14이다.") {
                result.score shouldBe PlayerScore(14)
            }
        }

        `when`("FOUR을 추가하면") {
            val result = playerHand.add(FakeGenerator.card(CardNumber.FOUR))

            then("점수는 17이다.") {
                result.score shouldBe PlayerScore(17)
            }
        }
    }

    given("카드가 TWO, THREE가 있을 때") {
        val playerHand = PlayerHand.init
            .add(
                listOf(
                    FakeGenerator.card(CardNumber.TWO),
                    FakeGenerator.card(CardNumber.THREE)
                )
            )

        `when`("ACE를 추가하면") {
            val result = playerHand.add(FakeGenerator.card(CardNumber.ACE))

            then("점수는 16이다.") {
                result.score shouldBe PlayerScore(16)
            }
        }
    }

    given("카드가 ACE가 있을 때") {
        val playerHand = PlayerHand.init
            .add(FakeGenerator.card(CardNumber.ACE))

        `when`("ACE를 추가하면") {
            val result = playerHand.add(FakeGenerator.card(CardNumber.ACE))

            then("점수는 12이다.") {
                result.score shouldBe PlayerScore(12)
            }
        }

        `when`("TEN, TEN을 추가하면") {
            val result = playerHand.add(
                List(2) { FakeGenerator.card(CardNumber.TEN) }
            )

            then("점수는 31이다.") {
                result.score shouldBe PlayerScore(31)
            }
        }
    }

    given("카드가 ACE, TEN이 있을 때") {
        val playerHand = PlayerHand.init
            .add(
                listOf(
                    FakeGenerator.card(CardNumber.ACE), FakeGenerator.card(CardNumber.TEN)
                )
            )
    }
})
