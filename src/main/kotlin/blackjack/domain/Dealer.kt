package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.player.Player

class Dealer(
    val deck: Deck = Card.allShuffled(),
) {
    fun dealCards(player: Player, count: Int) {
        repeat(count) {
            player.addCard(drawCard())
        }
    }

    private fun drawCard(): Card = deck.draw()
}
