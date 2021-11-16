package blackjack.domain.card

object CardFactory : CardGenerator {

    override fun generateCards(): List<Card> {
        return mutableListOf<Card>().apply {
            generateFromSuits()
        }
    }

    private fun MutableList<Card>.generateFromSuits() {
        enumValues<Suit>().map {
            addToCards(this, it)
        }
    }

    private fun addToCards(
        cards: MutableList<Card>,
        suit: Suit
    ) = enumValues<Denomination>().map {
        cards.add(Card(suit, it))
    }
}
