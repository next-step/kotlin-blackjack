package domain

class Dealer : CardHolder {
    private val hand = Cards()

    override fun receiveCard(card: Card) {
        hand.add(card)
    }

    fun calculateScore(): Int = hand.calculateScore()

    override fun showHand(): List<Card> = hand.showHand()
}
