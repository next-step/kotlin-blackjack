package blackjack.domain

class Player(
    private val dealer: Dealer,
    private val _cardSet: MutableSet<BlackjackCard> = mutableSetOf(),
) {
    init {
        repeat(INIT_CARD_COUNT) { drawCard() }
    }

    val cardSet: Set<BlackjackCard>
        get() = _cardSet

    fun drawCard() {
        val card = dealer.drawCard()
        _cardSet.add(card)
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
