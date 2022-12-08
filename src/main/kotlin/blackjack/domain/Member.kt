package blackjack.domain

sealed interface Member {

    val cards: Cards

    val cardElements: Set<Card>
        get() = cards.elements

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun resultScore() = this.cards.score()

    fun ableMoreDrawCard(): Boolean
}
