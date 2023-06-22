package blackjack.domain

object BlackJackTable {

    const val DEFAULT_CARD_SIZE = 2

    private val blackJackCards: MutableList<Card> = generateBlackJackCards()

    init {
        check(blackJackCards.size != blackJackCards.distinct().size) {
            "카드는 중복이면 안됨"
        }
    }

    fun hitCard() : Card {
        val card = blackJackCards.random()
        blackJackCards.remove(card)
        return card
    }

    private fun generateBlackJackCards(): MutableList<Card> {
        return CardNumber.values().flatMap {
            getCard(it)
        }.toMutableList()
    }

    private fun getCard(cardNumber: CardNumber) = CardType.values().map { cardType ->
        Card(cardNumber, cardType)
    }
}