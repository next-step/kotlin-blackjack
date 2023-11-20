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
    private val players = createPlayers()
    private val dealer = Dealer()

    fun start() {
        view.displayGameStartAnnouncement(players.getNames())
        dealInitialCards(players, cardDesk, dealer)
        executeCardDraws(players, drawConditionStrategy, cardDesk, dealer)
        displayGameResults(dealer, players)
    }

    private fun createPlayers(): Players {
        val playerNames = view.askForPlayerNames()
        return Players(playerNames.map { GamePlayer(it, PlayerCards(), view.askForBettingMoney(it)) })
    }

    private fun dealInitialCards(
        players: Players,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        dealer.addCards(cardDesk.startDraw())
        view.displayPlayerCards(dealer)
        players.dealInitialCards(cardDesk) { view.displayPlayerCards(it) }
    }

    private fun executeCardDraws(
        players: Players,
        drawConditionStrategy: DrawConditionStrategy,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        view.printNewLine()
        players.executeCardDraws(cardDesk, drawConditionStrategy, view::askForOneMore, view::displayPlayerCards)
        dealer.executeCardDraws(cardDesk) { view.displayDealerDrawCardAnnouncement() }
    }

    private fun displayGameResults(
        dealer: Dealer,
        players: Players
    ) {
        view.displayPlayerResult(dealer)
        players.forEachPlayer(view::displayPlayerResult)
        view.displayGameResult(players.getGameResult(dealer))
    }
}

fun main() {
    GameManager().start()
}
