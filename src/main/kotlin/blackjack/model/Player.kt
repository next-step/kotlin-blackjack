package blackjack.model

class Player(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    fun calculatePoint(): Int {
        var point = 0
        _myReceivedCard
            .map { it.getCardPoints() }
            .map { point += if (it.size == 1) it.first().points else calculateAceCase(point, it) }
        return point
    }

    fun isReachMaxPoint() = calculatePoint() < MAX_POINT

    private fun calculateAceCase(point: Int, it: List<Point>) =
        if (point + it.last().points <= MAX_POINT) it.last().points else it.first().points

    companion object {
        private const val MAX_POINT = 21
    }
}
