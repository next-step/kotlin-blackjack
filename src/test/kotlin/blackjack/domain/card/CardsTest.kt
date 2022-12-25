package blackjack.domain.card

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : BehaviorSpec({
    Given("기존 카드에 ") {
        val cards = Cards(
            mutableListOf(
                Card(CardShape.CLOVER, CardNumber.ACE),
            )
        )
        When("카드를 추가하면 ") {
            cards.add(Card(CardShape.HEART, CardNumber.ACE))
            Then("새로운 Cards가 만들어진다.") {
                cards.toString() shouldBe "A클로버, A하트"
            }
        }
    }

    Given("카드들이 주어졌을 때 ") {
        val firstCards = Cards(
            mutableListOf(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.CLOVER, CardNumber.NUM_2),
                Card(CardShape.CLOVER, CardNumber.KING),
            )
        )
        When("카드의 점수를 ") {
            val score = firstCards.calculate()
            Then("계산할 수 있다. [ACE가 11로 사용]") {
                score shouldBe 13
            }
        }

        val secondCards = Cards(
            mutableListOf(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
                Card(CardShape.CLOVER, CardNumber.KING),
            )
        )
        When("카드의 점수를 ") {
            val score = secondCards.calculate()
            Then("계산할 수 있다. [ACE가 1로 사용]") {
                score shouldBe 21
            }
        }

        val thirdCards = Cards(
            mutableListOf(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.CLOVER, CardNumber.NUM_2),
                Card(CardShape.CLOVER, CardNumber.KING),
                Card(CardShape.CLOVER, CardNumber.ACE),
            )
        )
        When("카드의 점수를 ") {
            val score = thirdCards.calculate()
            Then("계산할 수 있다. [ACE가 1, 11로 사용]") {
                score shouldBe 14
            }
        }

        When("카드의 이름들을 ") {
            Then("나열할 수 있다.") {
                secondCards.toString() shouldBe "A클로버, Q클로버, K클로버"
            }
        }

        When("카드의 개수들을 ") {
            Then("가져올 수 있다.") {
                secondCards.getCardSize() shouldBe 3
            }
        }
    }
})
