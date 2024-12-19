package blackjack.domain.state

import blackjack.domain.card.PlayingCard

abstract class Finished(private val hands: Hands) : State {
    override val cards: List<PlayingCard>
        get() = hands.toList()

    init {
        require(hands.size >= 2) { "Finished 상태는 최소한 2장을 갖고있어야 합니다." }
    }

    fun draw(card: PlayingCard): State {
        throw IllegalStateException("Finished 상태에서는 카드를 더 뽑을 수 없습니다.")
    }

}
