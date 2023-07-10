package blackjack.domain.player

import blackjack.domain.deck.Deck
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("플레이어는 게임시작시 카드를 두장씩 받는다.") {
        val deck = Deck.makeDeck()
        val player = Player.of("pavlo")
        When("게임이 시작해서 플레이어가 생성된이후 시작 카드를 받는다.") {
            player.drawStartHand(deck)
            Then("가지고 있는 카드의수가 2장이여야한다.")
            player.getHands().size shouldBe 2
        }
    }
    Given("플레이어는 카드를 더 받거나 멈출 수 있다") {
        val deck = Deck.makeDeck()
        val player = Player.of("pavlo")
        player.drawStartHand(deck)
        When("플레이어가 2장의 카드에서 카드를 더 받을때") {
            player.hit(deck)
            Then("플레이어의 카드 수가 3장이 되어야한다.") {
                player.getHands().size shouldBe 3
            }
        }
    }
})
