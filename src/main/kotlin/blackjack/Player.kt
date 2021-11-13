package blackjack

interface Player {
    fun receiveCard(card: Card): Gamer

    fun turnOff(): Gamer

    fun turnOn(): Gamer

    fun isMyTurn(): Boolean
}
