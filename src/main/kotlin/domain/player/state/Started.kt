package domain.player.state

import domain.card.CardState
import domain.card.PlayingCard
import domain.card.PlayingCards
import exception.IllegalDrawException
import exception.IllegalStayException

class Started(cards: PlayingCards) : Running(cards) {
    override fun stay(): PlayerState {
        throw IllegalStayException("시작할 때는 stay 할 수 없습니다.")
    }

    override fun draw(playingCard: PlayingCard): PlayerState {
        throw IllegalDrawException("시작할 때는 draw 할 수 없습니다.")
    }

    override fun nextState(): PlayerState = when (cardState()) {
        CardState.FINISHED -> Blackjack(this)
        else -> Hit(this)
    }
}
