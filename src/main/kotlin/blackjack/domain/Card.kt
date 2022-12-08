package blackjack.domain

data class Card(
    val number: Number,
    val sharp: Sharp
) {

    fun sumAllScore() = number.values.sum()
    fun isAce() = this.number == Number.ACE
}
