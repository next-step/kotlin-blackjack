package model

class Dealer(private var cards: Cards) {
    fun shuffle() {
        this.cards = cards.shuffle()
    }

    fun draw(): Card {
        return cards.pick()
    }

    fun cards(): List<Card> {
        return cards.list
    }
}
