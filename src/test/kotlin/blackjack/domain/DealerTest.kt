package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("트럼프 카드가 주어지면") {
        val trumpCard = TrumpCard.init()
        When("딜러에게 초기 카드를 주어질때") {
            val dealer = Dealer(trumpCard.drawFirstCards())
            Then("딜러의 카드는 2장이다.") {
                dealer.cards.cards.size shouldBe 2
            }
        }
    }

    given("딜러 카드가 2장이 주어졌다면") {
        val dealer = Dealer(
            Cards(
                Suit.SPADE to Rank.ACE,
                Suit.DIAMOND to Rank.FIVE,
            )
        )
        `when`("딜러 카드에 카드를 한장 더 뽑으면") {
            dealer.drawBy(Card(Suit.HEART, Rank.TWO))
            then("딜러 카드는 3장이다.") {
                dealer.cards.cards.size shouldBe 3
            }
        }
    }

    given("카드 A스페이드,5다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.DIAMOND to Rank.FIVE,
        )
        val dealer = Dealer(cards)
        When("카드 점수가 17점 미만이면") {
            val isHit = dealer.isHit()
            Then("히트이다.") {
                isHit shouldBe true
            }
        }
    }

    given("카드 A스페이드,6다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.DIAMOND to Rank.SIX,
        )
        val dealer = Dealer(cards)
        When("카드 점수가 17점 이상이면") {
            val isHit = dealer.isHit()
            Then("히트가 아니다.") {
                isHit shouldBe false
            }
        }
    }
})
