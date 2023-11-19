package blackjack.view

import blackjack.business.participants.Dealer
import blackjack.business.participants.Player
import blackjack.business.participants.Players

interface GameView {
    fun displayGameStartAnnouncement(playerNames: List<String>)
    fun displayDealerCards(dealer: Dealer)
    fun displayPlayerCards(player: Player)
    fun displayPlayerResult(player: Player)
    fun displayDealerResult(dealer: Dealer)
    fun displayGameResult(dealer: Dealer, players: Players)
    fun displayDealerDrawCardAnnouncement()
    fun askForOneMore(playerName: String): String
    fun askForPlayerNames(): String
}
