package blackjack.domain.player

import blackjack.domain.card.Card

class Participant(
    val name: String,
) : BlackjackFunction, RevenueFunction {

    init {
        require(name.isNotBlank()) { "이름은 공백제외 1글자 이상이어야 합니다." }
    }

    private var _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() {
            return _cards.toList()
        }
    private var revenue: Double = 0.0

    override fun addCard(card: Card) {
        _cards.add(card)
    }

    override fun getCardSum(): Int {
        return _cards
            .sortedByDescending { card -> card.getOrder() }
            .fold(START_INDEX) { sum, card ->
                sum + card.getValue(sum)
            }
    }

    override fun isOverDeadline(): Boolean = getCardSum() > DEADLINE

    override fun isBlackjack(): Boolean = getCardSum() == BLACKJACK_NUMBER

    override fun addRevenue(revenue: Double) {
        this.revenue += revenue
    }

    override fun minusRevenue(loss: Double) {
        this.revenue -= loss
    }

    override fun getRevenue(): Double = revenue

    companion object {
        private const val START_INDEX = 0
        private const val BLACKJACK_NUMBER = 21
        private const val DEADLINE = 21
    }
}
