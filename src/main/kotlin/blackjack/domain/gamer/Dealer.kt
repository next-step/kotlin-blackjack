package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.result.DealerResultType
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State

class Dealer private constructor(
    name: String,
    state: State,
) : Gamer(name, state) {

    init {
        validateName(name)
    }

    override fun prepare(deck: Deck): Dealer {
        val completedFirstDraw = draw(deck, state)
        val completedSecondDraw = draw(deck, completedFirstDraw)
        return Dealer(name, completedSecondDraw)
    }

    fun progress(currentScore: Int, deck: Deck): Dealer {
        if (currentScore >= DEALER_DRAW_CONDITION) {
            return meetConditions(currentScore)
        }
        return play(deck)
    }

    override fun play(deck: Deck): Dealer {
        val currentState = draw(deck, state)
        return Dealer(name, currentState)
    }

    private fun meetConditions(currentScore: Int): Dealer {
        if (currentScore == BLACKJACK_SCORE) {
            return Dealer(name, Blackjack(cards))
        }
        if (currentScore > BLACKJACK_SCORE) {
            return Dealer(name, Bust(cards))
        }
        return Dealer(name, Stand(cards))
    }

    fun judgeGameResult(player: Player): DealerResultType {
        val currentScore = this.currentScore()
        val playerScore = player.currentScore()
        return when {
            (currentScore == playerScore) -> DealerResultType.PUSH
            (player.isBlackjack() || player.isTwentyOne()) -> DealerResultType.LOSE
            (player.isBust()) -> DealerResultType.WIN
            (this.isBust() && !player.isBust()) -> DealerResultType.LOSE
            (currentScore < playerScore) -> DealerResultType.LOSE
            else -> DealerResultType.WIN
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 17

        fun from(cards: Cards): Dealer {
            return Dealer(DEALER_NAME, FirstDraw(cards))
        }

        fun init(name: String, state: State): Dealer {
            return Dealer(name, state)
        }
    }
}
