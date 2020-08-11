package blackjack.model

abstract class Gamer {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    var totalPoints = 0
        private set

    open fun requestCard(card: Card) {
        _myReceivedCard.add(card)
        calculatePoint(card)
    }

    private fun isAvailableExtraPoint(cardPoint: Int) =
        cardPoint == Kinds.ACE.point && totalPoints + cardPoint + ACE_EXTRA_POINT <= MAX_POINT

    private fun calculatePoint(card: Card) {
        val cardPoint = card.getCardKinds().point
        totalPoints += if (isAvailableExtraPoint(cardPoint)) cardPoint + ACE_EXTRA_POINT else cardPoint
    }

    fun isReachMaxPoint() = totalPoints >= MAX_POINT

    companion object {
        const val MAX_POINT = 21
        protected const val ACE_EXTRA_POINT = 10
    }
}
