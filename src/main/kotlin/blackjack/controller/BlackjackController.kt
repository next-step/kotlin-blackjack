package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.service.BettingService
import blackjack.service.BlackjackService
import blackjack.view.BlackjackResultView
import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private val blackjackService = BlackjackService()
    private val bettingService = BettingService()

    fun run() {
        val dealerAndPlayers = initializeParticipants()
        val dealer = dealerAndPlayers.first
        val players = dealerAndPlayers.second

        blackjackService.initialTurn(dealer, players)
        BlackjackView.printInitial(dealer, players)

        blackjackService.playGame(dealer, players)
        BlackjackResultView.printParticipantsResult(dealer, players)

        val result = bettingService.bettingResult(dealer, players)
        BlackjackResultView.printBettingResult(result)
    }

    private fun initializeParticipants(): Pair<Dealer, Players> {
        val dealer = Dealer()
        val playersNames = InputView.inputPlayersNames()
        val moneys = InputView.inputBettingMoney(playersNames)

        val playerList = buildList {
            playersNames.forEachIndexed { index, name ->
                add(Player(name, moneys[index]))
            }
        }
        val players = Players(playerList)
        return Pair(dealer, players)
    }
}

fun main() {
    BlackjackController().run()
}
