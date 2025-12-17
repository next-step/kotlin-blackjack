package domain

data class Player(override val name: String, override val cards: Cards = Cards()) : Participant {
    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
    }

    override fun getPublicCardsOnFirstRound(): Cards = cards

    override fun isDrawAvailable(): Boolean = cards.calculateScore() < BLACKJACK_SCORE
}
