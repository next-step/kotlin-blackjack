package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Score

class BlackJackPlayerResult(private val player: Player) : BlackJackGameResult {
    val playerName: String get() = player.name
    override val name: String get() = player.name
    override val cards get() = player.cards

    fun winLose(dealerScore: Score): String {
        return score.winLose(dealerScore).name()
    }
}
