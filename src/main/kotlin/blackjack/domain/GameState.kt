package blackjack.domain

class GameState(
    private val hands: Hands = Hands(),
    private val bet: Bet = Bet(0),
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    val cards: String
        get() = hands.toString()

    fun addCard(card: Card) {
        hands.add(card)
    }
}
