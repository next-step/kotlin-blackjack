package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DealerTest : BehaviorSpec({
    given("딜러는 처음받은 2장의 카드의 합이") {
        `when`("16점 이하이면") {
            val dealer = Dealer(
                name = "딜러",
                cards = Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.TWO),
                        Card(CardSuit.HEART, Denomination.TWO),
                    )
                )
            )
            val result = dealer.canHit()

            then("딜러는 카드를 더 받을 수 있다.") {
                result shouldBe true
            }
        }
        `when`("17점 이상이면") {
            val dealer = Dealer(
                name = "딜러",
                cards = Cards(
                    mutableListOf(
                        Card(CardSuit.CLOVER, Denomination.ACE),
                        Card(CardSuit.HEART, Denomination.TEN),
                    )
                )
            )
            val result = dealer.canHit()

            then("딜러는 카드를 받을 수 없다..") {
                result shouldBe false
            }
        }
    }
})
