package blackjack.domain

interface Player {
    val name: Name
    val cards: Cards
    val score: Int
        get() = cards.getScore()

    fun drawInitialCards(deck: Deck): Player {
        return this.copy(cards = deck.drawInitCards())
    }
    fun hit(card: Card) {
        cards.add(card)
    }

    fun isHit() = cards.getScore() <= TWENTY_ONE

    fun isBlackJack() = cards.isBlackJack()

    fun copy(name: Name = this.name, cards: Cards): Player

    companion object {
        private const val TWENTY_ONE = 21
    }
}
