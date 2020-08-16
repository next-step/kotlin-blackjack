package model

class Dealer {
    val cards: Cards
        get() = CARD_LIST

    fun draw(): Card {
        return cards.pick()
    }

    companion object {
        val CARD_LIST = Cards(Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } })
    }
}