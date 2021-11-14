package blackjack.application

import blackjack.domain.deck.Deck
import blackjack.domain.gamer.Player
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State

class BlackjackGame private constructor(
    val player: Player,
    val state: State,
) {

    fun completeDeal(deck: Deck): BlackjackGame {
        val completedFirstDraw = draw(deck, state)
        val completedDeal = draw(deck, completedFirstDraw)
        return BlackjackGame(player, completedDeal)
    }

    fun play(deck: Deck, sign: String): BlackjackGame {
        if (state.isStand(sign)) {
            return BlackjackGame(player, Stand(player.cards))
        }
        val currentState = draw(deck, state)
        return BlackjackGame(player, currentState)
    }

    private fun draw(deck: Deck, state: State): State {
        val card = deck.takeOut()
        return state.draw(card)
    }

    companion object {
        fun create(player: Player): BlackjackGame {
            val cards = player.cards
            return BlackjackGame(player, FirstDraw(cards))
        }
    }
}
