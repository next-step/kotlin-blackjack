package blackjack

import blackjack.business.participants.Dealer
import blackjack.business.participants.Player
import blackjack.business.participants.Players
import blackjack.business.util.Money
import blackjack.view.GameView

class GameManagerTestGameView : GameView {
    lateinit var playerNames: List<String>
    lateinit var dealer: Dealer
    lateinit var players: Players

    override fun displayGameStartAnnouncement(playerNames: List<String>) {
        this.playerNames = playerNames
    }

    override fun displayPlayerCards(player: Player) = Unit

    override fun displayPlayerResult(player: Player) = Unit

    override fun displayGameResult(dealer: Dealer, players: Players) {
        this.players = players
        this.dealer = dealer
    }

    override fun displayDealerDrawCardAnnouncement() = Unit

    override fun askForOneMore(playerName: String): String = "n"

    override fun askForPlayerNames(): List<String> = listOf("pobi", "jason")
    override fun printNewLine() = Unit
    override fun askForBettingMoney(playerName: String): Money {
        return Money(10000)
    }
}
