package domain

data class Player(
    override val name: String,
    override val cards: Cards = Cards(),
    val bettingAmount: Long,
) : Participant {
    init {
        require(name.isNotBlank()) { "참가자 이름은 빈 값일 수 없습니다." }
        require(bettingAmount > 0) { "참가자의 베팅 금액은 0보다 커야 합니다." }
    }

    override fun getPublicCardsOnFirstRound(): Cards = cards

    fun isBlackjack(): Boolean = cards.hasTwoCards() && cards.isBlackjackScore()
}
