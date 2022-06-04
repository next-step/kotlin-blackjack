package blackjack.domain.card

data class Card(
    val pattern: CardPattern,
    val score: CardScore
) {
    companion object {
        fun createDeck(): List<Card> {
            return ArrayList<Card>().apply {
                CardPattern.values().forEach {
                    this.addAll(CardScore.of(it))
                }
            }.shuffled().toList()
        }
    }
}