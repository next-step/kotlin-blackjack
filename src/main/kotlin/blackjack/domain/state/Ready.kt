package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Ready(private val hands: Hands = Hands()) : State {
    override val cards: List<PlayingCard>
        get() = hands.toList()
    constructor(card: PlayingCard) : this(Hands(listOf(card)))

    fun draw(card: PlayingCard): State {
        val hands = hands + card
        if (hands.size < NEXT_STATE_HANDS_SIZE) return Ready(card)
        if (Hands(hands).isBlackJackScore()) return Blackjack(hands)
        return Hit(hands)
    }

    companion object{
        private const val NEXT_STATE_HANDS_SIZE: Int = 2
    }
}
