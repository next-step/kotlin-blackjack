package blackjack.model

class Player(val name: String) {

    private val _myReceivedDeck = mutableListOf<Deck>()
    val myReceivedDeck: List<Deck> get() = _myReceivedDeck

    fun requestDeck(deck: Deck) {
        _myReceivedDeck.add(deck)
    }

    fun calculateRank(): Int {
        var rank = 0
        _myReceivedDeck
            .map { it.getDeckPoints() }
            .map { rank += if (it.size == 1) it.first().points else calculateAceCase(rank, it) }
        return rank
    }

    private fun calculateAceCase(rank: Int, it: List<Card.Point>) =
        if (rank + it.last().points <= MAX_RANK) it.last().points else it.first().points

    companion object {
        const val MAX_RANK = 21
    }
}
