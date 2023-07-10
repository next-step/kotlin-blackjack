package blackjack.domain.player

import blackjack.domain.deck.Deck

class Players private constructor(val players: List<Player>) {

    fun prepareGame(deck: Deck) {
        players.forEach {
            it.drawStartHand(deck)
        }
    }

    companion object {
        fun of(names: List<String>): Players {
            return Players(names.map { Player(it) }.toList())
        }
    }
}
