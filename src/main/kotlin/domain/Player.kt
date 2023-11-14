package domain

class Player(val name: String) : CardHolder {
    private val cards = mutableListOf<Card>()
    private val calculator = Calculator()

    override fun receiveCard(card: Card) {
        cards.add(card)
        calculator.receiveCard(card)
    }

    fun calculateScore(): Int = calculator.calculateScore()
    override fun showHand(): List<Card> = cards.toList()
}
