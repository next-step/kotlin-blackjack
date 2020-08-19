package blackjack.domain

import blackjack.domain.deck.Deck

class Participants(val players: List<Player>) {
    constructor(names: Collection<String>) : this(names.map { Player(it) })

    fun addCards(count: Int) {
        players.forEach {
            for (i in 1..count) {
                it.addCard(Deck.poll())
            }
        }
    }

    override fun toString() = players.joinToString { it.name }
}
