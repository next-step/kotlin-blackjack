package blackjack.domain.player

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Player

internal class Customer(name: String, betting: Int = 10000) : Player(name, betting) {
    override val maxHittableScore: Int get() = BlackJackGame.MAX_SCORE

    override val visibleCards: List<Card>
        get() = this.cards
}
