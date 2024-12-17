package blackjack.domain

class Player(
    val name: String,
    val betAmount: BetAmount,
    val hand: PlayerCards = PlayerCards(),
) {
    var status: PlayerStatus = PlayerStatus.HIT
        private set

    init {
        validateName(name)
    }

    fun addCard(card: Card): Boolean {
        val result = hand.addCard(card)
        status = changeStatus()
        return result
    }

    fun isBust(): Boolean {
        return status == PlayerStatus.BUST
    }

    fun stay() {
        status = PlayerStatus.STAY
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACKJACK
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "유저의 이름은 공백일 수 없습니다." }
    }

    private fun changeStatus(): PlayerStatus {
        return when {
            hand.calculateCardsMaxSum() == PlayerCards.ZERO -> PlayerStatus.BUST
            hand.cards.size() == 2 && hand.calculateCardsMaxSum() == Cards.GAME_LIMIT_NUMBER -> PlayerStatus.BLACKJACK
            else -> PlayerStatus.HIT
        }
    }
}
