package domain.gamer.dealer

import domain.Score
import domain.State
import domain.card.CardDeck
import domain.card.Cards
import domain.gamer.Gamer
import domain.gamer.player.Players

class Dealer(
    override val cards: Cards = Cards(),
    initialState: State = State.Hit,
    override val name: String = "딜러",
    var onHit: (() -> Unit)? = null,
) : Gamer {

    private var state: State = initialState

    val isBust: Boolean
        get() = state == State.Bust

    override val isHit: Boolean
        get() = state == State.Hit

    override fun hit(cardDeck: CardDeck) {
        if (state != State.Hit) return
        cards.add(cardDeck.pop())
        state = newState()
        onHit?.invoke()
    }

    fun winners(players: Players): Players {
        return players.playersWithMatchedScore(maxScore(players))
    }

    fun losers(players: Players): Players {
        return players.playersWithMismatchedScore(maxScore(players))
    }

    private fun maxScore(players: Players): Int {
        return players.notBustedPlayers()
            .results()
            .filter {
                it > this.score
            }.maxOrNull() ?: Int.MAX_VALUE
    }

    private fun newState(): State {
        val result = cards.score()
        return when {
            result > Score.BLACKJACK -> State.Bust
            result == Score.BLACKJACK -> State.BlackJack
            result > Score.DEALER_HIT_ON_MAX -> State.Stay
            else -> State.Hit
        }
    }
}
