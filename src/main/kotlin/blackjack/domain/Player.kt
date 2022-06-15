package blackjack.domain

class Player(
    val name: String,
    private val playerCards: PlayerCards = PlayerCards(),
    val betMoney: Money = Money(0),
    val rule: Rule = PlayerRule,
) {
    val cardCount: Int get() = playerCards.size
    val score: Score get() = playerCards.score

    fun draw(cardDeck: CardDeck) {
        playerCards.addCard(cardDeck.pop())
    }

    fun openedCards() = rule.openedCards(playerCards)

    fun currentCards() = playerCards.cards

    fun canDraw(): Boolean = rule.canDraw(score)

    fun isBust(): Boolean = playerCards.isBust()

    fun isBlackJack(): Boolean = playerCards.size == 2 && score.isBlackJack()
}
