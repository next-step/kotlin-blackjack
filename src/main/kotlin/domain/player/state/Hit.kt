package domain.player.state

import domain.card.CardState
import domain.card.PlayingCard
import domain.card.PlayingCards

class Hit(cards: PlayingCards) : Running(cards) {
    constructor(other: PlayerState) : this(other.cards)

    override fun stay(): PlayerState = Stay(this)
    override fun draw(playingCard: PlayingCard): PlayerState = Hit(cards + playingCard).nextState()
    override fun nextState(): PlayerState = when (cardState()) {
        CardState.BUST -> Bust(this)
        CardState.FINISHED -> Stay(this)
        CardState.RUNNING -> Hit(this)
    }
}
