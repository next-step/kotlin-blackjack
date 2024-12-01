package blackjack.entity

open class Participant(val name: String) {
    val hand = Hand()

    fun receiveCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int {
        return hand.calculateScore()
    }

    fun isBusted(): Boolean {
        return hand.isBusted()
    }
}
