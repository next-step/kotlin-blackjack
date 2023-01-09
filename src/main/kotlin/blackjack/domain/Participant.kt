package blackjack.domain

abstract class Player(val name: String, val myCards: Cards = Cards()) {

    val totalScore: Int
        get() = myCards.totalScore

    abstract fun canDraw(): Boolean

    abstract fun makeReport(others: List<Player>): Report

    fun draw(card: Card) {
        myCards.add(card)
    }

    operator fun plus(players: List<Player>): List<Player> {
        val list = players.toMutableList()
        list.add(this)
        return list
    }
}
