package blackjack.domain.state

import blackjack.domain.card.PlayingCard

class Hit(override val cards: List<PlayingCard>) : State {
    constructor(card: PlayingCard) : this(listOf(card))
    fun draw(card: PlayingCard): State {
        val hands = cards + card
        if(hands.size >= 2) return Hit(hands)
        return Ready(card)
    }

}
