package blackjack.domain.gamer

import blackjack.domain.money.Money

class Player(
    val name: String,
    val betAmount: Money,
) : Gamer() {

    override fun canHit(): Boolean {
        return state.isHit()
    }

    fun captureCards(): PlayerCards {
        return PlayerCards(
            playerName = name,
            cards = state.cards,
        )
    }
}
