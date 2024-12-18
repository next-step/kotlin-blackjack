package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Hit(private val hands: Hands) : State {
    override val cards: List<PlayingCard>
        get() = cards.toList()

    init {
        require(hands.size >= 2) { "Hit 상태는 최소한 2장을 갖고있어야 합니다." }
    }
    fun draw(card: PlayingCard): State {
        val hands = hands + card
        if(hands.score() > 21) return Bust(hands)
        return Hit(hands + card)
    }

    fun stay(clubsTwo: PlayingCard): State {
        return Stay(hands)
    }


}
