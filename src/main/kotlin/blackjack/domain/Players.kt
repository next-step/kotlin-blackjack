package blackjack.domain

class Players(private val players: List<Player>) : List<Player> by players {
    fun filterReceivable(): List<Player> {
        return players.filter { it.canReceiveOneMoreCard() }
    }

    fun getNames(): String {
        return players.joinToString(", ") { it.name }
    }

    fun receiveInitialCards(initialCards: () -> Cards) {
        players.forEach { it.receiveInitialCards(initialCards.invoke()) }
    }

    operator fun plus(players: Players): Players {
        return Players(this.players + players)
    }
}
