package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Ready(override val hands: Hands = Hands()) : Running() {
    constructor(card: PlayingCard) : this(Hands(listOf(card)))

    override fun draw(card: PlayingCard): State {
        val hands = hands + card
        if (hands.size < NEXT_STATE_HANDS_SIZE) return Ready(card)
        if (hands.isBlackJackScore()) return Blackjack(hands)
        return Hit(hands)
    }

    override fun stay(card: PlayingCard): State {
        throw IllegalArgumentException("게임이 종료되지 않았습니다.")
    }

    companion object{
        private const val NEXT_STATE_HANDS_SIZE: Int = 2
    }
}
