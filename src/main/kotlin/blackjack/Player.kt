package blackjack

class Player(val name: String) {
    private val takeCards = ArrayList<Card>()

    fun takeCard(card: Card) {
        takeCards.add(card)
    }

    fun getTakeCards(): List<Card> = takeCards

    companion object {
        const val ALLOW_TEXT = "y"
    }
}
