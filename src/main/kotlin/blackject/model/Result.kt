package blackject.model

interface Result {
    fun rate(): EarningRate
    fun profit(amount: Amount): Double
}
