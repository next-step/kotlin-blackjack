package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain

class PlayerTest : BehaviorSpec({

    Given("덱이 주어졌을 때") {
        val firstCard = Card(number = CardNumber.TEN, pattern = CardPattern.HEART)
        val secondCard = Card(number = CardNumber.TEN, pattern = CardPattern.DIAMOND)
        val player = Player(
            name = ParticipantName("플레이어1"),
            firstCard = firstCard,
            secondCard = secondCard,
        )
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()

        When("플레이어가 한 장 받게 되면") {
            player.receiveCard(card)

            Then("플레이어의 카드는 한 장 증가한다.") {
                player.cards().values shouldContain card
            }
        }
    }
})
