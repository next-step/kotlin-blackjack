package blackjack.model

class Player(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    fun calculatePoint(): Int =
        _myReceivedCard
            .map { it.getCardKinds().point }
            .reduce { acc, point ->
                acc + point + if (isAvailableExtraPoint(acc, point)) ACE_EXTRA_POINT else 0
            }

    fun isReachMaxPoint() = calculatePoint() >= MAX_POINT

    private fun isAvailableExtraPoint(acc: Int, point: Int): Boolean {
        return point == Kinds.ACE.point && acc + point + ACE_EXTRA_POINT < MAX_POINT
    }

    companion object {
        private const val MAX_POINT = 21
        private const val ACE_EXTRA_POINT = 10
    }
}
