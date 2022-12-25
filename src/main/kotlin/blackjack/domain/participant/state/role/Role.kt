package blackjack.domain.participant.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.participant.Player
import blackjack.domain.participant.state.Name

sealed class Role {
    abstract val name: Name

    abstract val state: State

    abstract fun draw(playingCard: PlayingCard): Player

    abstract fun stay(): Player

    abstract fun isDealer(): Boolean

    fun getScore(): Int {
        return state.cards.getScore()
    }

    fun isBlackjack(): Boolean {
        return state.cards.isBlackjack()
    }

    fun isBust(): Boolean {
        return state.cards.isBust()
    }

    fun getCardsSize(): Int {
        return state.cards.size()
    }

    fun getCardsAsListString(): List<String> {
        return state.cards.toListString()
    }
}
