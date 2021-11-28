package blackject.model

/**
 * 수익롤 관리 클래스
 * */
enum class EarningRate(val rate: Double) {
    BlackJack(1.5), Lose(-1.0), Win(1.0), Bust(-1.0);

    companion object {
        fun isPlusProfit(earningRate: EarningRate): Boolean {
            return earningRate.rate > 0
        }
    }
}
