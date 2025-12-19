package domain

abstract class Participant(
    protected val blackjackCards: BlackjackCards = BlackjackCards()
) {

    var profit: SignedMoney = SignedMoney(0)
        protected set

    abstract fun openFirstRound(): List<Card>

    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize(): Int = blackjackCards.getCards().size

    fun displayCardInfo(): String = blackjackCards.displayCardInfo()

    fun isBust(): Boolean = blackjackCards.calculateScore() > BlackjackCards.BLACKJACK_MAX_SCORE

    fun isBlackjack(): Boolean =
        blackjackCards.getCards().size == 2 && blackjackCards.calculateScore() == BlackjackCards.BLACKJACK_MAX_SCORE

    fun applyProfit(profit: SignedMoney) {
        this.profit = profit
    }
}