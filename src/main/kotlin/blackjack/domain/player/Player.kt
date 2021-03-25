package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.MatchResult

class Player(name: Name, cards: Cards) : Participant(name, cards) {

    fun match(dealer: Dealer) = when (score.compareTo(dealer.score)) {
        WIN -> MatchResult.WIN
        LOSE -> MatchResult.LOSE
        else -> MatchResult.DRAW
    }

    override fun toString(): String {
        return "Player(name=$name, cards=$cards)"
    }

    companion object {
        private const val WIN = 1
        private const val LOSE = -1
    }
}
