package domain

abstract class AbstractCardHolder : CardHolder {
    private val hand = Cards()

    fun calculateScore(): Int = hand.calculateScore()

    override fun receiveCard(card: Card) {
        hand.add(card)
    }
    override fun showHand(): List<Card> = hand.showHand()
}
