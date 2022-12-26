package blackjack.domain.participant.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.state.State
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.state.Name

sealed class Role {
    abstract val name: Name

    abstract val state: State

    abstract fun isDealer(): Boolean

    fun draw(playingCard: PlayingCard): Role {
        if (isDealer()) {
            return Dealer(state.draw(playingCard))
        }
        return Player(name, state.draw(playingCard))
    }

    fun stay(): Role {
        if (isDealer()) {
            return Dealer(state.stay())
        }
        return Player(name, state.stay())
    }

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
