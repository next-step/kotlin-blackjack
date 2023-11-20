package blackjack.view

import blackjack.business.participants.Dealer
import blackjack.business.participants.Player
import blackjack.business.participants.Players
import blackjack.business.util.Money

interface GameView {
    fun displayGameStartAnnouncement(playerNames: List<String>)
    fun displayPlayerCards(player: Player)
    fun displayPlayerResult(player: Player)
    fun displayGameResult(dealer: Dealer, players: Players)
    fun displayDealerDrawCardAnnouncement()
    fun printNewLine()
    fun askForOneMore(playerName: String): String
    fun askForPlayerNames(): List<String>
    fun askForBettingMoney(playerName: String): Money
}
