package blackjack.domain.state

import blackjack.domain.card.PlayingCard

abstract class Finished(final override val hands: Hands) : State {
    abstract val profitRate: Double

    init {
        require(hands.size >= 2) { "Finished 상태는 최소한 2장을 갖고있어야 합니다." }
    }

    override fun draw(card: PlayingCard): State {
        throw IllegalArgumentException("Finished 상태에서는 카드를 더 뽑을 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalArgumentException("Finished 상태에서는 stay를 할 수 없습니다.")
    }

    override fun isRunning(): Boolean {
        return false
    }

    override fun profit(money: Int): Double = money * profitRate
}
