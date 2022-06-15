package blackjack.domain

class Player(
    name: String,
    playerCards: PlayerCards = PlayerCards(),
    val betMoney: Money = Money(0),
) : User(name, playerCards) {
    override fun openedCards(): List<Card> = playerCards.cards

    override fun canDraw(): Boolean = !score.isBust()
}
