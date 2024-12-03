package blackjack.domain

abstract class Participant(private val name: PlayerName, private val hand: Hand) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun displayHand(): String {
        return hand.getCards().joinToString(", ") { it.display() }
    }

    fun calculateTotal(): Int {
        return hand.calculateBestTotal()
    }

    fun getName(): String {
        return name.value
    }

    fun compare(other: Participant): GameMatchResult {
        return when {
            calculateTotal() > other.calculateTotal() -> GameMatchResult.WIN
            calculateTotal() < other.calculateTotal() -> GameMatchResult.LOSE
            else -> GameMatchResult.DRAW
        }
    }

    abstract fun isDrawable(): Boolean
}
