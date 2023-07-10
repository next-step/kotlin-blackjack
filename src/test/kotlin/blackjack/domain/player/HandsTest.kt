package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HandsTest : BehaviorSpec({
    Given("손에 Ace를 포함해서 가지고 있는 수의 합이 11이하 일때 10 을 더해주어야한다.") {
        When("가지고 있는 수가 11이하 일때") {
            val hands = Hands(
                mutableSetOf(
                    Card(NumberShape.ACE, Pattern.SPADE),
                    Card(NumberShape.KING, Pattern.CLUB)
                )
            )
            Then("Ace 가 1이 아닌 11로 계산된다.") {
                hands.getCardsValue() shouldBe 21
            }
        }
        When("가지고 있는 수가 11보다 클떄") {
            val hands = Hands(
                mutableSetOf(
                    Card(NumberShape.ACE, Pattern.SPADE),
                    Card(NumberShape.KING, Pattern.CLUB),
                    Card(NumberShape.NINE, Pattern.HEART)
                )
            )
            Then("Ace는 1로 계산된다.") {
                hands.getCardsValue() shouldBe 20
            }
        }
    }
    Given("손에 숫자들만 있을 경우 올바른 계산이 되어야한다.") {
        When("가지고있는 수의 합이 18일 경우") {
            val hands = Hands(
                mutableSetOf(
                    Card(NumberShape.NINE, Pattern.SPADE),
                    Card(NumberShape.NINE, Pattern.HEART)
                )
            )
            Then("점수가 18점이 된다.") {
                hands.getCardsValue() shouldBe 18
            }
        }
    }
})
