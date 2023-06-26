package blackjack.domain.player

import blackjack.domain.PlayerResults

class DealerRevenue {
    private var resultValue: Long = INIT_REVENUE

    fun getRevenue(): Long {
        return resultValue
    }

    fun calcRevenue(playerResults: PlayerResults) {
        resultValue = playerResults.getDealerRevenue()
    }

    companion object {
        const val INIT_REVENUE = 0L
    }
}
