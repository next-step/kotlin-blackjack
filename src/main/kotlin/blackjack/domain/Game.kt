package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckShuffleStarategy

class Game(deckShuffleStrategy: DeckShuffleStarategy) {

    private val deck = Deck(deckShuffleStrategy)

    fun firstDraw(playerList: List<Player>) {
        playerList.forEach {
            it.addCards(deck.drawTwoCard())
        }
    }

    fun onePlayerDraw(player: Player) {
        player.addCard(deck.drawCard())
    }
}
