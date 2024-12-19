package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Hit(override val hands: Hands) : Running() {
    init {
        require(hands.size >= 2) { "Hit 상태는 최소한 2장을 갖고있어야 합니다." }
    }

    override fun draw(card: PlayingCard): State {
        val hands = hands + card
        if (hands.score() > 21) return Bust(hands)
        return Hit(hands)
    }

    override fun stay(card: PlayingCard): State {
        return Stay(hands)
    }
}
