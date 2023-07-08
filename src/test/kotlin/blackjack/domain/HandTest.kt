package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.test.FakeGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    given("카드의 총합이 21보다 작은 경우") {
        val hand = FakeGenerator.handOf20()

        `when`("ACE를 추가하면") {
            val result = hand.add(FakeGenerator.card(CardNumber.ACE))

            then("ACE는 1로 계산된다.") {
                result.score shouldBe Score(21)
            }
        }
    }

    given("카드의 총합이 21이상인 경우") {
        val hand = FakeGenerator.handOf21()

        `when`("ACE를 추가하면") {
            val result = hand.add(FakeGenerator.card(CardNumber.ACE))

            then("ACE는 11로 계산된다.") {
                result.score shouldBe Score(22)
            }
        }
    }

    given("ACE가 있을 때") {
        val hand = Hand.init
            .add(FakeGenerator.card(CardNumber.ACE))

        `when`("추가되는 카드합과 상관없이") {
            val result = hand.add(
                List(2) { FakeGenerator.card(CardNumber.TEN) }
            )

            then("ACE는 11로 계산된다.") {
                result.score shouldBe Score(31)
            }
        }
    }
})
