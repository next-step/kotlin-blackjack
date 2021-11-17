package domain.player.state

import domain.card.CardState
import domain.card.PlayingCard
import domain.card.PlayingCards

class Hit(cards: PlayingCards) : Running(cards) {
    constructor(other: PlayerState) : this(other.cards)
    constructor(other: PlayerState, card: PlayingCard) : this(other.cards + card)

    override fun stay() = Stay(this)
    override fun draw(playingCard: PlayingCard) = Hit(this, playingCard).nextState()
    override fun nextState() = when (cardState()) {
        CardState.BUST -> Bust(this)
        CardState.FINISHED -> Stay(this)
        CardState.RUNNING -> Hit(this)
    }
}
