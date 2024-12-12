package blackjack

sealed class Finished(cards: Cards) : Started(cards) {
    override fun draw(card: Card): State = throw IllegalStateException("이미 종료되어 카드를 뽑을 수 없는 상태입니다")

    override fun stay(): State = this

    override fun isFinished(): Boolean = true

    override fun profit(betAmount: Money): Money {
        return betAmount * earningRate()
    }

    abstract fun earningRate(): Double
}
