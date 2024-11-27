class Player(
    val name: String,
    private val cards: MutableList<Card> = mutableListOf(),
) {
    val currentCards: List<DrawCard>
        get() = cards
            .map { it.toDrawCard() }
            .toList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun totalValue(): Int =
        cards.fold(0) { acc, card -> acc + card.value(acc) }
}
