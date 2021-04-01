package blackjack

data class Game(private var cards: Cards) {
    val state: States
        get() {
            val score = cards.score
            return States.valueOf(score)
        }

    fun draw(card: Card) {
        cards = Cards(cards + card)
    }

    constructor(firstCard: Card, secondCard: Card) : this(Cards(listOf(firstCard, secondCard)))

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE = 10
    }
}
