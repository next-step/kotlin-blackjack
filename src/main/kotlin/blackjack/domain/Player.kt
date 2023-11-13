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
        return Cards(setOf(cards.cards.first(), cards.cards.elementAt(1)))
    }

    override fun isHit(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun stand() {
        status = PlayerStatus.STAND
    }
}
