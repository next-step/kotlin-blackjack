package blackjack.entity

abstract class Participant(val name: String) {
    companion object {
        const val BLACKJACK = 21
    }

    val hand = Hand()

    fun receiveCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int {
        return hand.calculateScore()
    }

    fun isBusted(): Boolean = hand.isBusted()

    fun isBlackjack(): Boolean = hand.isBlackJack()

    fun closeToBlackjack(score: Int): Int {
        return BLACKJACK - score
    }
}
