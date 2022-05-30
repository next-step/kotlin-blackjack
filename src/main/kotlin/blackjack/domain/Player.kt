package blackjack.domain

data class Player(val name: String, val hand: Hand) {

    val score: Int
        get() = TODO()

    fun addCard(card: Card) {
        TODO("Not yet implemented")
    }
}
