package blackjack.domain

object CardFactory {

    val cards = createAllCards()

    private fun createAllCards(): List<Card> {
        return Symbol.values().flatMap { symbol ->
            Type.values().map {  type ->
                Card(symbol, type)
            }
        }
    }
}
