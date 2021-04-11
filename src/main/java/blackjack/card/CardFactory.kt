package blackjack.card

object CardFactory {

    fun makeCards(): List<Card> {
        val cards = mutableListOf<Card>()
        CardType.values().forEach { type ->
            CardValue.values().forEach { value ->
                cards.add(Card(type, value))
            }
        }
        return cards
    }
}
