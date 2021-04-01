package blackjack

class Player(private var cards: Cards) {
    var state: States = States.HIT
        get() {
            return States.valueOf(cards.score, field)
        }

    fun draw(card: Card) {
        if (state == States.STAY) {
            throw IllegalStateException("STAY이므로 draw 할 수 없습니다.")
        }

        cards = cards.addCard(card)
    }

    fun stop() {
        state = States.STAY
    }

    constructor(firstCard: Card, secondCard: Card): this(Cards(firstCard, secondCard))
}
