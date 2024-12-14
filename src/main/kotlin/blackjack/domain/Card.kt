package blackjack.domain

data class Card(
    val number: CardNumber,
    val mark: CardMark,
    val isFaceUp: Boolean = true,
) {
    fun isAce(): Boolean {
        return this.number == CardNumber.ACE
    }
}
