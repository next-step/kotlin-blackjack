package blackjack.domain.gamer

import blackjack.domain.game.MatchResultType
import blackjack.domain.game.Money
import blackjack.domain.game.PlayerMatchResult

class Player(
    val name: String,
    val betAmount: Money,
) : Gamer() {

    override fun canHit(): Boolean {
        return state.isHit()
    }

    fun match(dealer: Dealer): PlayerMatchResult {
        return PlayerMatchResult(
            playerName = name,
            matchResultType = MatchResultType.calculatePlayerMatchResult(dealer, this),
        )
    }

    fun captureCards(): PlayerCards {
        return PlayerCards(
            playerName = name,
            cards = state.cards,
        )
    }
}
