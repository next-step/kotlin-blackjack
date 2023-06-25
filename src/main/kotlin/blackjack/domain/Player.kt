package blackjack.domain

class Player(
    val cards: MutableList<Card> = mutableListOf(), var score: Int = 0
) {
}