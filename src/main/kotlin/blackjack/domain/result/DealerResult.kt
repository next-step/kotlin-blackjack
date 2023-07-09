package blackjack.domain.result

data class DealerResult(val finalRevenue: Int = 0) {
    fun dealerFinalRevenue(revenue: Int): DealerResult {
        return copy(finalRevenue = finalRevenue - revenue)
    }
}
