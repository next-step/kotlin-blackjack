package blackjack.model

object Pack {
    private val PackOfCard: Map<Card, Boolean> = config()

    // 카드가 남아있으면 참, 나가있으면 거짓
    private fun config(): Map<Card, Boolean> {
        val map: MutableMap<Card, Boolean> = mutableMapOf()
        for (s in Suit.values()) {
            for (r in Rank.values()) {
                map[Card(s, r)] = true
            }
        }
        return map
    }

    fun anyCard(): Card {
        return this.PackOfCard.keys.filter { requireNotNull(PackOfCard[it]) }.shuffled().first()
    }
}
