package blackjack.domain.player

import blackjack.domain.PlayerResults

class DealerRevenue {
    var value: Long = INIT_REVENUE
        private set

    fun calcRevenue(playerResults: PlayerResults) {
        value = playerResults.getDealerRevenue()
    }

    companion object {
        const val INIT_REVENUE = 0L
    }
}
