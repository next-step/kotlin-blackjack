package blackjack.domain

object CardSet {
    fun create(): List<Card> {
        val cardSet = mutableListOf<Card>()
        CardType.values().forEach { cardType ->
            CardValue.values().forEach {
                cardSet.add(Card(cardType = cardType, cardValue = it))
            }
        }
        return cardSet.toList()
    }

    fun pickCard(cardSet: List<Card>): Pair<Card, List<Card>> {
        val copy = cardSet.toMutableList()
        val pickCard = copy.shuffled().take(1).last()
        copy.remove(pickCard)
        return Pair(pickCard, copy.toList())
    }
}
