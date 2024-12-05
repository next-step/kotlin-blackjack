package blackjack.domain

data class Card(
    val number: CardNumber,
    val mark: CardMark,
) {
    fun isAce(): Boolean {
        return this.number == CardNumber.ACE
    }
}
