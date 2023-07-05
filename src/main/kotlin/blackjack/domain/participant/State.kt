package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.BlackJack
import blackjack.domain.game.Rank

open class State(val cards: Cards = Cards()) {

    operator fun plus(card: Card) {
        cards.addCard(card)
    }

    fun score() = cards.score()
    private fun isBlackJack() = cards.isBlackJack()
    private fun isBurst() = score() > BlackJack.BLACKJACK_MAX_SCORE


    fun getRank(dealerState: State): Rank {
        return when {
            isBlackJack() && dealerState.isBlackJack() -> Rank.DRAW
            isBlackJack() -> Rank.BLACKJACK
            score() == dealerState.score() -> Rank.DRAW
            dealerState.isBurst() -> Rank.WON
            isBurst() -> Rank.LOST
            score() > dealerState.score() -> Rank.WON
            score() < dealerState.score() -> Rank.LOST
            else -> Rank.DRAW
        }
    }
}
