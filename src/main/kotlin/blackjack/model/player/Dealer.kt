package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.game.Rank
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.Bust
import blackjack.model.state.playState.gameState.Hit

data class Dealer(var state: State) {
    constructor(cards: CardDeck = CardDeck()) : this(Hit(cards))

    fun draw(card: Card) {
        this.state = state.draw(card)
    }

    fun isOverSixTeen() = score() <= SIXTEEN

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
        state !is Bust && player.isNotBust() && (score() == player.score())

    private fun isWinningCondition(player: Player) = (state is Bust && player.isNotBust()) ||
        (state !is Bust && player.isNotBust() && (score() < player.score()))

    companion object {
        private const val SIXTEEN = 16
    }
}
