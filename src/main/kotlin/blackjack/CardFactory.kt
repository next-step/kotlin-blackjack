package blackjack

object CardFactory : CardGenerator {

    override fun generateCards(): List<Card> {
        return mutableListOf<Card>().apply {
            generateFromSuits()
        }
    }

    private fun MutableList<Card>.generateFromSuits() {
        enumValues<Suit>().map {
            addToCards(this, it)
        }.shuffled().toList()
    }

    private fun addToCards(
        cards: MutableList<Card>,
        suit: Suit
    ) = enumValues<Denomination>().map {
        cards.add(Card(suit, it))
    }
}
