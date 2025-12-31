package domain

data class Player(override val name: String, override var money: Int, override val cards: Cards = Cards()) :
    Participant {
    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
        require(money > 0) { "베팅 금액은 0 보다 커야합니다." }
    }

    override fun getPublicCardsOnFirstRound(): Cards = cards

    override fun isDrawAvailable(): Boolean = cards.calculateScore() < BLACKJACK_SCORE

    fun getCardSize(): Int = cards.cards().size

    fun multiplyMoney(multiply: Double) {
        money = (money * multiply).toInt()
    }
}
