package blackjack.domain.player

interface RevenueFunction {
    fun addRevenue(revenue: Double)
    fun minusRevenue(loss: Double)
    fun getRevenue(): Double
}
