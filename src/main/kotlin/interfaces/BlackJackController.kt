package interfaces

import application.BlackJackGame
import common.Executable
import domain.player.Participants
import domain.player.Playable
import interfaces.ui.CardInfo
import interfaces.ui.InputConsole
import interfaces.ui.OutputConsole

class BlackJackController : Executable {

    override fun execute() {

        val participants = queryParticipants()
        val blackJackGame = BlackJackGame(players = participants)

        initGame(blackJackGame)
        showAllParticipantsHands(blackJackGame)

        queryReceiveCard(blackJackGame)

        dealerReceiveCard(blackJackGame)

        showFinalResult(blackJackGame)
    }

    private fun initGame(blackJackGame: BlackJackGame) {
        blackJackGame.init()
        val playerNames = blackJackGame.allPlayers().map { it.name }
        OutputConsole.printInit(playerNames = playerNames)
    }

    private fun queryParticipants(): Participants {
        val nameList = InputConsole.queryParticipantName()
        return Participants.from(names = nameList)
    }

    private fun showAllParticipantsHands(blackJackGame: BlackJackGame) {
        val dealer = blackJackGame.dealer()
        val dealerCardInfo = cardInfo(dealer)
        OutputConsole.printCard(playerName = "딜러", cardInfo = dealerCardInfo)

        val players = blackJackGame.allPlayers()
        players.forEach { player ->
            val cardInfo = cardInfo(player)
            OutputConsole.printCard(playerName = player.name, cardInfo = cardInfo)
        }
    }

    private fun cardInfo(playable: Playable): List<CardInfo> {
        return playable.hands.cardList().map {
            CardInfo(
                number = it.number.nameValue,
                name = it.shape.nameValue
            )
        }
    }

    private fun queryReceiveCard(blackJackGame: BlackJackGame) {
        OutputConsole.printNewLine()
        val allPlayers = blackJackGame.allPlayers()
        allPlayers.forEach {
            blackJackGame.playsTurn(player = it) { player -> InputConsole.queryReceiveCard(player.name) }
            val cardInfo = cardInfo(it)
            OutputConsole.printCard(playerName = it.name, cardInfo = cardInfo)
        }
    }

    private fun dealerReceiveCard(blackJackGame: BlackJackGame) {
        val dealer = blackJackGame.dealer()
        val availableReceiveCard = blackJackGame.availableReceiveCard(dealer)
        if (availableReceiveCard) {
            blackJackGame.receiveCard(dealer)
            OutputConsole.printDealerMoreReceiveCard()
        }
    }

    private fun showFinalResult(blackJackGame: BlackJackGame) {
        OutputConsole.printNewLine()

        val dealer = blackJackGame.dealer()
        val dealerCardInfo = cardInfo(dealer)
        val resultDealerScore = dealer.handsCardScore()
        OutputConsole.printCardWithResult(playerName = "딜러", cardInfo = dealerCardInfo, result = resultDealerScore.toString())

        val players = blackJackGame.allPlayers()
        players.forEach { player ->
            val cardInfo = cardInfo(player)
            val resultScore = player.handsCardScore()
            OutputConsole.printCardWithResult(playerName = player.name, cardInfo = cardInfo, result = resultScore.toString())
        }

        var dealerWinCount = 0
        val resultPlayerBoards = blackJackGame.allPlayers().map {
            val isPlayerWin = dealer.isWin(it)
            if (!isPlayerWin) dealerWinCount++
            ResultPlayerBoard(name = it.name, isWin = isPlayerWin)
        }
        val resultDealerBoard = ResultDealerBoard(players.size, dealerWinCount)

        OutputConsole.printFinalResult(resultDealerBoard, resultPlayerBoards)
    }

    data class ResultDealerBoard(
        val totalCount: Int,
        val winCount: Int
    )

    data class ResultPlayerBoard(
        val name: String,
        val isWin: Boolean
    )
}
