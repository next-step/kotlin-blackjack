package blackjack

class Player(
    val name: String,
    val cards: Cards
) {
    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun resultScore() = this.cards.score()

    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }

    companion object {
        fun init(name: String, deck: Deck): Player {
            val cardA = deck.draw()
            val cardB = deck.draw()

            return Player(name, Cards(cardA, cardB))
        }
    }
}
