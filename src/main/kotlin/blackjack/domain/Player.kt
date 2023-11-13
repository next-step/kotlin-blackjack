package blackjack.domain

class Player(
    override val name: String,
    override val cards: Cards = Cards()
) : BlackJackPlayer() {

    init {
        if (isBlackJack()) {
            blackjack()
        }
    }

    override fun firstOpenCards(): Cards {
        return cards.take(FIRST_OPEN_COUNT)
    }

    override fun isHit(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun stand() {
        status = PlayerStatus.STAND
    }

    companion object {
        private const val FIRST_OPEN_COUNT = 2
    }
}
