package blackjack

data class Game(private var cards: Cards) {
    val state: States
        get() {
            var score = cards.sumBy { it.number.value }

            for (card in cards) {
                score = calculateAce(card, score)
            }

            if (score < BLACK_JACK_SCORE) {
                return States.HIT
            } else if (score == BLACK_JACK_SCORE) {
                return States.BLACK_JACK
            }

            return States.BUST
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
