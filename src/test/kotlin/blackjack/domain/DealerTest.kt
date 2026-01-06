package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러가 주어졌을 때") {
        val dealer = Dealer()

        Then("이름은 딜러다") {
            dealer.name shouldBe "딜러"
        }
    }

    Given("딜러가 주어졌을 때") {
        val dealer = Dealer()

        When("두장의 카드값이 합이 16이하면") {
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.ACE))

            Then("카드 추가 획득") {
                dealer.isAdditionalDealCondition() shouldBe true
            }
        }
    }

    Given("딜러가 주어졌을 때") {
        val dealer = Dealer()

        When("두장의 카드값이 합이 16초과이면") {
            dealer.addCard(Card(Suit.DIAMOND, Rank.JACK))
            dealer.addCard(Card(Suit.SPADE, Rank.JACK))

            Then("카드 추가 획득 X") {
                dealer.isAdditionalDealCondition() shouldBe false
            }
        }
    }

    Given("딜러가 주어졌을 때") {
        val dealer = Dealer()

        When("카드를 3장까지 추가하면") {
            dealer.addCard(Card(Suit.DIAMOND, Rank.ACE))
            dealer.addCard(Card(Suit.SPADE, Rank.TWO))
            dealer.addCard(Card(Suit.HEART, Rank.THREE))

            Then("카드가 정상적으로 추가된다.") {
                dealer.score() shouldBe 16
            }
        }
    }
})
