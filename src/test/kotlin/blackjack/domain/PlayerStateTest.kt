package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

private fun card(number: CardNumber): Card {
    return Card(number, CardShape.values().random())
}

class PlayerStateTest : BehaviorSpec({
    given("카드가 TEN, THREE가 있을 때") {
        val playerState = PlayerState.init
            .add(
                listOf(
                    card(CardNumber.TEN),
                    card(CardNumber.THREE)
                )
            )

        `when`("ACE를 추가하면") {
            val result = playerState.add(card(CardNumber.ACE))

            then("점수는 14이다.") {
                result.score shouldBe PlayerScore(14)
            }
        }

        `when`("FOUR을 추가하면") {
            val result = playerState.add(card(CardNumber.FOUR))

            then("점수는 17이다.") {
                result.score shouldBe PlayerScore(17)
            }
        }

        `when`("canAddCard()를 호출하면") {
            val result = playerState.canAddCard()

            then("true이다.") {
                result shouldBe true
            }
        }
    }

    given("카드가 TWO, THREE가 있을 때") {
        val playerState = PlayerState.init
            .add(
                listOf(
                    card(CardNumber.TWO),
                    card(CardNumber.THREE)
                )
            )

        `when`("ACE를 추가하면") {
            val result = playerState.add(card(CardNumber.ACE))

            then("점수는 16이다.") {
                result.score shouldBe PlayerScore(16)
            }
        }

        `when`("canAddCard()를 호출하면") {
            val result = playerState.canAddCard()

            then("true이다.") {
                result shouldBe true
            }
        }
    }

    given("카드가 ACE가 있을 때") {
        val playerState = PlayerState.init
            .add(card(CardNumber.ACE))

        `when`("ACE를 추가하면") {
            val result = playerState.add(card(CardNumber.ACE))

            then("점수는 12이다.") {
                result.score shouldBe PlayerScore(12)
            }
        }

        `when`("TEN, TEN을 추가하면") {
            val result = playerState.add(
                List(2) { card(CardNumber.TEN) }
            )

            then("점수는 31이다.") {
                result.score shouldBe PlayerScore(31)
            }
        }

        `when`("canAddCard()를 호출하면") {
            val result = playerState.canAddCard()

            then("true이다.") {
                result shouldBe true
            }
        }
    }

    given("카드가 ACE, TEN이 있을 때") {
        val playerState = PlayerState.init
            .add(
                listOf(
                    card(CardNumber.ACE), card(CardNumber.TEN)
                )
            )

        `when`("canAddCard()를 호출하면") {
            val result = playerState.canAddCard()

            then("false이다.") {
                result shouldBe false
            }
        }
    }
})
