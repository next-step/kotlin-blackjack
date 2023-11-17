package blackjack

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.UserInputBasedDrawCondition
import blackjack.business.participants.Dealer
import blackjack.business.participants.Players
import blackjack.view.DetailedDealerOutputHandler
import blackjack.view.DetailedPlayerOutputHandler
import blackjack.view.GameResultOutputHandler
import blackjack.view.InputHandler
import blackjack.view.PlayerNameParser
import blackjack.view.SimpleDealerCardOutputHandler
import blackjack.view.SimplePlayerOutputHandler

object GameManager {
    fun start() {
        val cardDesk = CardDesk()
        val players = createPlayers()
        val dealer = Dealer()
        val drawConditionStrategy = UserInputBasedDrawCondition()
        dealInitialCards(players, cardDesk, dealer)
        executeCardDraws(players, drawConditionStrategy, cardDesk, dealer)
        displayGameResults(dealer, players)
    }

    private fun createPlayers(): Players {
        val playerNames = InputHandler.askForPlayerNames()
        return Players.from(PlayerNameParser.parse(playerNames))
    }

    private fun dealInitialCards(
        players: Players,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        println()
        println("딜러와 ${players.allPlayers.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        val cards = cardDesk.startDraw()
        dealer.addCards(cards)
        SimpleDealerCardOutputHandler.print(dealer)
        players.forEachPlayer {
            val playerCards = cardDesk.startDraw()
            it.addCards(playerCards)
            SimplePlayerOutputHandler.print(it)
        }
    }

    private fun executeCardDraws(
        players: Players,
        drawConditionStrategy: UserInputBasedDrawCondition,
        cardDesk: CardDesk,
        dealer: Dealer
    ) {
        println()
        players.forEachPlayer {
            while (it.canDrawCard() && drawConditionStrategy.shouldDraw(it.name)) {
                it.addCard(cardDesk.draw())
                SimplePlayerOutputHandler.print(it)
            }
        }
        if (dealer.canDrawCard()) {
            println()
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            dealer.addCard(cardDesk.draw())
        }
    }

    private fun displayGameResults(
        dealer: Dealer,
        players: Players
    ) {
        println()
        DetailedDealerOutputHandler.print(dealer)
        players.forEachPlayer(DetailedPlayerOutputHandler::print)
        GameResultOutputHandler.print(dealer, players)
    }
}

fun main() {
    GameManager.start()
}
