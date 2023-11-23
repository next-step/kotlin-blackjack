package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.player.CardHolder
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player

class Dealer(
    val deck: Deck = Card.allShuffled(),
    val dealerPlayer: DealerPlayer = DealerPlayer(),
) : CardHolder by dealerPlayer {
    fun dealCards(count: Int, vararg players: Player) {
        players.forEach { dealCards(count, it) }
    }

    infix fun dealToSelf(count: Int) {
        repeat(count) {
            hand.add(drawCard())
        }
    }

    private fun dealCards(count: Int, player: Player) {
        repeat(count) {
            player.addCard(drawCard())
        }
    }

    private fun drawCard(): Card = deck.draw()
}
