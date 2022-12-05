package blackjack

data class Card(
    val number: Number,
    val sharp: Sharp
) {

    fun sumAllScore(): Int {
        return number.values.sum()
    }
}
