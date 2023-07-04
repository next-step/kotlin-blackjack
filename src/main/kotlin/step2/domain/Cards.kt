package step2.domain

class Cards(
    val cards: MutableSet<Card> = mutableSetOf()
) {

    fun draw(count: Int) {
        cards.addAll(CardDeck.draw(count))
    }
}
