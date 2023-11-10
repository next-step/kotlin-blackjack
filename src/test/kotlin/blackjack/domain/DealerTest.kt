package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    given("트럼프 카드가 주어지면") {
        val trumpCard = TrumpCard.init()
        When("딜러에게 초기 카드를 주어질때") {
            val dealer = Dealer(trumpCard.firstCardDraw())
            Then("딜러의 카드는 2장이다.") {
                dealer.cardSet.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 딜러 2장을 받았다면") {
        val trumpCard = TrumpCard.init()
        val dealer = Dealer(trumpCard.firstCardDraw())
        `when`("딜러 카드에 카드를 한장 더 뽑으면") {
            dealer.drawBy(trumpCard)
            then("딜러 카드는 3장이다.") {
                dealer.cardSet.size shouldBe 3
            }
        }
    }

    given("카드 A스페이드,5다이아몬드를 받았다면") {
        val cards = Cards(
            Suit.SPADE to Rank.ACE,
            Suit.DIAMOND to Rank.FIVE,
        )
        val dealer = Dealer(cards)
        When("히트 여부를 확인할 때") {
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
        When("히트 여부를 확인할 때") {
            val isHit = dealer.isHit()
            Then("히트가 아니다.") {
                isHit shouldBe false
            }
        }
    }
})
