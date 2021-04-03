package blackjack.domain.participants

import blackjack.domain.winning.BettingResult

class Players(
    val values: List<Player>
) {
    fun getPlayersEarnRate(dealer: Dealer): BettingResult {
        return BettingResult(
            values.associateWith { it.getEarnRate(dealer) }
        )
    }

    override fun toString(): String {
        return values.joinToString { it.name }
    }
}
