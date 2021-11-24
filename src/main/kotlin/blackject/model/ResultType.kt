package blackject.model

/**
 * 게임 결과
 * */
fun ResultType.profit(amount: Amount): Double = earningRate.rate.times(amount.value)

enum class ResultType(val earningRate: EarningRate) {
    Bust(EarningRate.Bust),
    BlackJack(EarningRate.BlackJack),
    Lose(EarningRate.Lose),
    Win(EarningRate.Win);
}
