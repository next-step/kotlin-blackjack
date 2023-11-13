package blackjack.domain

class BlackJackPlayerResult(private val player: Player, private val winLose: WinLose) {
    val name: String get() = player.name
    val cards: Cards get() = player.cards
    val score: Score get() = cards.score()
    val firstOpenCards: Cards get() = player.firstOpenCards()
    val winLoseMoney: Money get() = player.winLoseMoney(winLose)
}
