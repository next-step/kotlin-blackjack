package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러카드") {
        val dealer = Dealer()

        Then("이름은 딜러다") {
            dealer.name shouldBe "딜러"
        }

        When("두장의 카드값이 합이 16이하면") {
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.ACE))

            Then("카드 추가 획득") {
                dealer.isAdditionalDealCondition() shouldBe true
            }
        }

        When("두장의 카드값이 합이 16초과이면") {
            dealer.addCard(Card(Suit.DIAMOND, Rank.JACK))
            dealer.addCard(Card(Suit.SPADE, Rank.JACK))

            Then("카드 추가 획득 X") {
                dealer.isAdditionalDealCondition() shouldBe false
            }
        }
    }
})
