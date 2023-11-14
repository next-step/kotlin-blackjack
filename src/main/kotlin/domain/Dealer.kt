package domain

class Dealer : CardHolder {
    private val cards: MutableList<Card> = mutableListOf()
    private val calculator = Calculator()

    override fun receiveCard(card: Card) {
        cards.add(card)
        calculator.receiveCard(card)
    }

    fun calculateScore(): Int = calculator.calculateScore()
    override fun showHand(): List<Card> = cards.toList()
}
