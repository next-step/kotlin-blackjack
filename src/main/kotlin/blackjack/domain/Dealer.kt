package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.player.CardHolder
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player

class Dealer(
    val deck: Deck = Card.allShuffled(),
    val player: DealerPlayer = DealerPlayer(),
) : CardHolder by player {
    fun dealCards(player: Player, count: Int) {
        repeat(count) {
            player.addCard(drawCard())
        }
    }

    infix fun dealToSelf(count: Int) {
        repeat(count) {
            hand.add(drawCard())
        }
    }

    private fun drawCard(): Card = deck.draw()
}
