package blackjack.domain

abstract class BlackjackParticipant(val name: String, var bettingAmount: Int = 0) {
    private var _cards = Cards(mutableListOf())
    var profit = 0.0
    val isBusted get() = getScore() > Score.BLACKJACK
    val isBlackjack get() = this.getScore() == Score.BLACKJACK
    val cards: Cards
        get() = _cards

    fun getScore(): Int {
        return Score(cards.cards.map { it.denomination }).calculate()
    }

    fun getFirstDealCards(twoCards: List<Card>) {
        twoCards.forEach { _cards.addCard(it) }

    }

    fun hit(card: Card) {
        cards.addCard(card)
    }

    abstract val canHit: Boolean
}
