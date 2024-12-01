package blackjack.entity

import blackjack.entity.Dealer.Companion.MAX_SCORE_TO_DRAW
import blackjack.entity.Dealer.Companion.MIN_SCORE_TO_STAND
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러의 초기 카드 합계가 특정 조건일 때") {
        When("딜러의 합계가 $MAX_SCORE_TO_DRAW 이하일 경우") {
            val dealer = Dealer()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SIX))

            Then("추가로 카드 한장을 받을 수 있다") {
                dealer.shouldDrawCard() shouldBe true
            }
        }

        When("딜러의 합계가 $MIN_SCORE_TO_STAND 이상일 경우") {
            val dealer = Dealer()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SEVEN))

            Then("추가로 카드를 받을 수 없다") {
                dealer.shouldDrawCard() shouldBe false
            }
        }
    }
})
