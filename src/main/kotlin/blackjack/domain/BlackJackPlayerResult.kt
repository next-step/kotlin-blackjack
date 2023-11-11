package blackjack.domain

class BlackJackPlayerResult(private val blackJackPlayer: BlackJackPlayer) {
    val name: String get() = blackJackPlayer.name
    val cards: Cards get() = blackJackPlayer.cards
    val score: Score get() = cards.score()
    val firstOpenCards: Cards get() = blackJackPlayer.firstOpenCards()
}
