package blackjack.domain

object CardSet {
    fun create(): List<Card> {
        val cardSet = mutableListOf<Card>()
        CardType.values().forEach { cardType ->
            CardValue.values().forEach {
                cardSet.add(Card(cardType = cardType, cardValue = it))
            }
        }
        return cardSet
    }
}
