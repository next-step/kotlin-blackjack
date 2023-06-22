package blackjack

class Cards(val value: MutableList<Card> = mutableListOf()) {
    fun initCards(cards: List<Card>) {
        this.value.addAll(cards)
    }

    fun addCard(card: Card) {
        value.add(card)
    }
}
