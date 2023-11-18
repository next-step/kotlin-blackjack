package blackjack.model

object CardDealer {
    fun getCards(count: Int): List<Card> = (1..count).map {
        getCard()
    }

    fun getCard(): Card {
        val value = CardValue.gerRandom()
        val suit = CardSuit.gerRandom()
        return Card("${value.cardValue}${suit.cardSuit}", value)
    }
}
