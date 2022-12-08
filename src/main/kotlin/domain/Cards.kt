package domain

class Cards {
    private val mutableCards = mutableListOf<Card>()

    val cards: List<Card>
        get() = mutableCards.toList()

    fun add(card: Card) {
        mutableCards.add(card)
    }

}
