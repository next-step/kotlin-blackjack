package blakjack.domain

class Player(
    val name: String,
) {
    var cards: Cards = Cards.empty()
        private set

    val score: Int
        get() = cards.score()

    val isUnderBlackjackScore: Boolean
        get() = cards.scoreWithAceAsOne() < BLACKJACK_SCORE

    fun add(card: Card) {
        this.cards = cards.add(card)
    }

    fun add(cards: Cards) {
        this.cards = cards.add(cards)
    }
}
