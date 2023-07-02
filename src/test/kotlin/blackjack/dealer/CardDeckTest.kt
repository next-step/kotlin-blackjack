package blackjack.dealer

import blackjack.player.Player
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : BehaviorSpec({

    Given("게임이 시작되면") {
        When("딜러에 의해 카드 덱이 생성될 때") {
            Then("초기 카드 덱은 52장이다.") {
                CardDeck.size shouldBe 52
            }
        }
        When("딜러가 두장을 참가자에게 나누어주면") {
            Then("카드 덱은 50장이다.") {
                val dealer = Dealer()
                val players = listOf(Player("참가자"))
                dealer.dealInitialCards(players)
                CardDeck.size shouldBe 48
            }
        }
    }
})
