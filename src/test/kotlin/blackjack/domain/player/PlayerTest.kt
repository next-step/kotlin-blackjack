package blackjack.domain.player

import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    Given("덱이 주어졌을 때") {
        val player = Player(
            name = PlayerName("플레이어1"),
            cards = PlayerCards()
        )
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()

        When("플레이어가 한 장 받게 되면") {
            player.handCard(card)

            Then("플레이어의 카드는 한 장 증가한다.") {
                player.cards.values.size shouldBe 1
                player.cards.values shouldBe listOf(card)
            }
        }
    }
})
