package blackjack.view

import blackjack.domain.BlackJackPlayer
import blackjack.domain.Cards
import blackjack.domain.Score

class BlackJackPlayerResult(private val blackJackPlayer: BlackJackPlayer) {
    val name: String get() = blackJackPlayer.name
    val cards: Cards get() = blackJackPlayer.cards
    val score: Score get() = cards.score()

    val firstOpenCards: Cards get() = blackJackPlayer.firstOpenCards()
}
