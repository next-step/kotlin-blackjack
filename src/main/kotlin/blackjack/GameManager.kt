package blackjack

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.DrawConditionStrategy
import blackjack.business.drawConditionStrategy.UserInputBasedDrawCondition
import blackjack.business.participants.Dealer
import blackjack.business.participants.Player
import blackjack.business.participants.Players
import blackjack.business.util.PlayerNameParser
import blackjack.view.ConsoleGameView
import blackjack.view.GameView

class GameManager(
    private val view: GameView,
    private val drawConditionStrategy: DrawConditionStrategy = UserInputBasedDrawCondition()
) {
    private val cardDesk = CardDesk()
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
        return Players(PlayerNameParser.parse(playerNames).map { Player(it) })
    }

    private fun dealInitialCards(
        players: Players,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        dealer.addCards(cardDesk.startDraw())
        view.displayDealerCards(dealer)
        players.dealInitialCards(cardDesk) { view.displayPlayerCards(it) }
    }

    private fun executeCardDraws(
        players: Players,
        drawConditionStrategy: DrawConditionStrategy,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        println()
        players.executeCardDraws(cardDesk, drawConditionStrategy, view::askForOneMore, view::displayPlayerCards)
        dealer.executeCardDraws(cardDesk) { view.displayDealerDrawCardAnnouncement() }
    }

    private fun displayGameResults(
        dealer: Dealer,
        players: Players
    ) {
        println()
        view.displayDealerResult(dealer)
        players.forEachPlayer(view::displayPlayerResult)
        view.displayGameResult(dealer, players)
    }
}

fun main() {
    val gameManager = GameManager(view = ConsoleGameView())
    gameManager.start()
}
