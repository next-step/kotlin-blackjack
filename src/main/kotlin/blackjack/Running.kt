package blackjack

sealed class Running(cards: Cards) : Started(cards) {
    override fun isFinished(): Boolean = false

    override fun profit(betAmount: Money): Money = throw IllegalStateException("종료된 상태가 아니라 수익을 구할 수 없습니다")
}
