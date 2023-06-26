package blackjack.domain

import blackjack.util.FixedCardsSelector
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : BehaviorSpec({

    Given("게임이 하나 주어졌다") {
        val cardSelector = FixedCardsSelector(
            Card(Suit.SPADE, CardNumber.ACE),
            Card(Suit.DIAMOND, CardNumber.ACE),
            Card(Suit.HEART, CardNumber.ACE),
            Card(Suit.CLOVER, CardNumber.ACE),
        )
        val game = BlackjackGame(cardSelector)

        When("게임 시작시 시작패를 뽑으면") {
            Then("2개씩 뽑아진다") {
                game.getInitDeck().size shouldBe 2
            }
        }

        When("딜러의 패를 확인하면") {
            Then("2장을 가지고 있다") {
                game.getDealerDeck().size shouldBe 2
            }
        }
    }
})
