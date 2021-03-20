package blackjack.domain.player

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Player

internal class Customer(name: String) : Player(name) {
    override val visibleCards: List<Card> = this.cards
    override fun canHit(): Boolean = this.score() < BlackJackGame.MAX_SCORE
}
