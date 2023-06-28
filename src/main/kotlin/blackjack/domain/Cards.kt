package blackjack.domain

class Cards(
    val card: MutableList<Card> = mutableListOf()
) {
    fun initCard(card: List<Card>) {
        this.card.addAll(card)
    }

    fun addCard(card: Card) {
        this.card.add(card)
    }
}
