package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.game.Rank
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.Hit

data class Dealer(var state: State) {
    constructor(cards: CardDeck = CardDeck()) : this(Hit(cards))

    fun draw(card: Card) {
        this.state = state.draw(card)
    }

    fun hasPassedHurdle() = score() <= SCORE_HURDLE

    fun cards(): List<Card> {
        return state.cards()
    }

    fun score() = state.calculateScore()

    fun isWin(player: Player): Rank {
        return when {
            isWinningCondition(player) -> Rank.WIN
            isDrawCondition(player) -> Rank.DRAW
            else -> Rank.LOSE
        }
    }

    private fun isDrawCondition(player: Player) =
        !state.isBust() && !player.isBust() && (score() == player.score())

    private fun isWinningCondition(player: Player) = (state.isBust() && !player.isBust()) ||
        (!state.isBust() && !player.isBust() && (score() < player.score()))

    companion object {
        private const val SCORE_HURDLE = 16
    }
}
