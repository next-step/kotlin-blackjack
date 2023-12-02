package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.player.Player
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("플레이어와 딜러가 있을 때") {
        val player = Player(
            name = ParticipantName("플레이어1")
        )
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        When("딜러가 카드를 분배하면") {
            dealer.handCard(player)

            Then("플레이어의 카드는 한 장 증가한다.") {
                player.cards.values.size shouldBe 1
            }
        }
    }
})
