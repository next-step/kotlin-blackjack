package blackjack

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.DrawConditionStrategy
import blackjack.business.drawConditionStrategy.UserInputBasedDrawCondition
import blackjack.business.participants.Dealer
import blackjack.business.participants.GamePlayer
import blackjack.business.participants.PlayerCards
import blackjack.business.participants.Players
import blackjack.view.ConsoleGameView
import blackjack.view.GameView

class GameManager(
    private val view: GameView = ConsoleGameView(),
    private val drawConditionStrategy: DrawConditionStrategy = UserInputBasedDrawCondition(),
    private val cardDesk: CardDesk = CardDesk()
) {
    private var players = createPlayers()
    private var dealer = Dealer()

    fun start() {
        view.displayGameStartAnnouncement(players.getNames())
        dealInitialCards()
        executeCardDraws()
        displayGameResults()
    }

    private fun createPlayers(): Players {
        val playerNames = view.askForPlayerNames()
        return Players(playerNames.map { GamePlayer(it, PlayerCards(), view.askForBettingMoney(it)) })
    }

    private fun dealInitialCards() {
        dealer = dealer.addCards(cardDesk.startDraw())
        view.displayPlayerCards(dealer)
        players = players.dealInitialCards(cardDesk) { view.displayPlayerCards(it) }
    }

    private fun executeCardDraws() {
        view.printNewLine()
        players.executeCardDraws(cardDesk, drawConditionStrategy, view::askForOneMore, view::displayPlayerCards)
            .also { players = it }
        dealer.executeCardDraws(cardDesk) { view.displayDealerDrawCardAnnouncement() }.also { dealer = it }
    }

    private fun displayGameResults() {
        view.displayPlayerResult(dealer)
        players.forEachPlayer(view::displayPlayerResult)
        view.displayGameResult(players.getGameResult(dealer))
    }
}

fun main() {
    GameManager().start()
}
