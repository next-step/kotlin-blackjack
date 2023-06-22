package blackjack.domain.card

class Cards(val value: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        value.add(card)
    }
    companion object {
        fun from(vararg card:Card): Cards {
            return Cards(card.toMutableList())
        }
    }
}
