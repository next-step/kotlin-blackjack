package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards()
) : BlackJackPlayer(name, cards) {

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
