package blackjack.domain

class Players(val players: List<Player>) {
    // deck에서 2장씩 가져가라
    fun addCards(deck: Deck, count: Int) {
        players.forEach {
            for (i in 1..count) {
                it.addCard(deck.take())
            }
        }
    }

    fun isFinished(): Boolean {
        return players.all { it.isBust || it.isStay }
    }

    override fun toString() = players.joinToString { it.name }

    constructor(names: Collection<String>) : this(names.map { Player(it) })
}
