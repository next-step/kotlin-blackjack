package blackjack.view

import blackjack.business.participants.GameResult
import blackjack.business.participants.Player
import blackjack.business.util.Money

interface GameView {
    fun displayGameStartAnnouncement(playerNames: List<String>)
    fun displayPlayerCards(player: Player)
    fun displayPlayerResult(player: Player)
    fun displayGameResult(gameResult: GameResult)
    fun displayDealerDrawCardAnnouncement()
    fun printNewLine()
    fun askForOneMore(playerName: String): String
    fun askForPlayerNames(): List<String>
    fun askForBettingMoney(playerName: String): Money
}
