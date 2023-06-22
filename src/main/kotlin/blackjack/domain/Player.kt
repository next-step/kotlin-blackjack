package blackjack.domain

class Player(val name: String, size: Int) {

    val cards: MutableList<Card> = mutableListOf()

    init {
        repeat(size) {
            cards.add(BlackJackTable.hitCard())
        }
    }
    fun addCard(card: Card) {
        cards.add(card)
    }

}
