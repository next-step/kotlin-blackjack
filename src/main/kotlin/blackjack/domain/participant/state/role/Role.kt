package blackjack.domain.participant.state.role

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.State
import blackjack.domain.card.state.rule.Blackjack
import blackjack.domain.card.state.rule.Hit
import blackjack.domain.participant.state.Name
import blackjack.domain.participant.state.result.Result

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

    fun calculateResult(score: Int): Result {
        val playerScore = getScore()
        return when {
            playerScore > score -> Result.Win
            playerScore == score -> Result.Draw
            else -> Result.Lose
        }
    }

    fun hasOnlyTwoCards(): Boolean {
        return state.cards.size() == NUMBER_OF_STARTING_CARDS
    }

    companion object {
        internal const val NUMBER_OF_STARTING_CARDS = 2

        fun initState(cards: PlayingCards): State {
            if (cards.isBlackjack()) {
                return Blackjack(cards)
            }
            return Hit(cards)
        }
    }
}
