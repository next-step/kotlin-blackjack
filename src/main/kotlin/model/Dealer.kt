package model

class Dealer {
    private lateinit var cards: Cards

    init {
        shuffle(Cards())
    }

    fun shuffle(cards: Cards) {
        this.cards = cards.shuffle()
    }
    fun draw(): Card {
        return cards.pick()
    }

    fun cards(): List<Card> {
        return cards.list
    }
}
