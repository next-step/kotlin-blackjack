package blackjack.domain

object BlackJackTable {

    val blackJackCards: List<Card>

    init {
        blackJackCards = generateBlackJackCards()
    }

    private fun generateBlackJackCards(): List<Card> {
        return CardNumber.values().flatMap {
            getCard(it.number)
        }
    }

    private fun getCard(cardNumber: String) = CardType.values().map { cardType ->
        Card(cardNumber, cardType)
    }
}