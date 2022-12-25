package blackjack.domain.player.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.player.Player
import blackjack.domain.player.state.Name

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
