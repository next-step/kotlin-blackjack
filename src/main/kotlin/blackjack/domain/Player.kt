package blackjack.domain

interface Player {
    fun receiveCard(card: Card): Player

    fun turnOff(): Player

    fun turnOn(): Player

    fun isMyTurn(): Boolean

    fun openCards(): Cards

    fun getPlayerName(): Name

    fun getHighestPoint(): Int
}
