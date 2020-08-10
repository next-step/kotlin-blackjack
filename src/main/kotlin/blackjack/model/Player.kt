package blackjack.model

class Player(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    fun calculateRank(): Int {
        var rank = 0
        _myReceivedCard
            .map { it.getCardPoints() }
            .map { rank += if (it.size == 1) it.first().points else calculateAceCase(rank, it) }
        return rank
    }

    private fun calculateAceCase(rank: Int, it: List<Point>) =
        if (rank + it.last().points <= MAX_RANK) it.last().points else it.first().points

    companion object {
        const val MAX_RANK = 21
    }
}
