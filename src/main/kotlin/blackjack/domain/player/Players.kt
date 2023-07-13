package blackjack.domain.player

import blackjack.domain.card.Card

class Players private constructor(val players: List<Player>) {

    fun race(card: Card, continueGame: (Player) -> Boolean, afterDrawCard: (Player) -> Unit) {
        players.forEach {
            it.race(card, continueGame, afterDrawCard)
        }
    }

    companion object {
        fun of(names: List<String>): Players {
            return Players(names.map { Player(it) }.toList())
        }
    }
}
