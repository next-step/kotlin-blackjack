package blackjack.domain

abstract class Player(
    open val name: String
) {
    abstract val canReceiveCard: Boolean

    private val _cardList: MutableList<Card> = mutableListOf()
    val cardList: List<Card>
        get() = _cardList.toList()

    val score: Int
        get() {
            var score = cardList.sumOf { it.rank.point }

            if (cardList.any { it.rank == Rank.ACE } && score <= BLACKJACK_SCORE - ACE_EXTRA_POINT) {
                score += ACE_EXTRA_POINT
            }
            return score
        }

    var money: Int = 0

    abstract fun receiveFirstTurnCard(blackjackShoe: BlackjackShoe)

    fun receiveCard(card: Card) {
        _cardList.add(card)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_EXTRA_POINT = 10
    }
}
