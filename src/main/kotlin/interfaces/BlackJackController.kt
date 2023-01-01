package interfaces

import application.BlackJackGame
import common.Executable
import domain.player.Participants
import domain.player.Player
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
        val players = blackJackGame.allPlayers()
        players.forEach { player ->
            val cardInfo = cardInfo(player)
            OutputConsole.printCard(playerName = player.name, cardInfo = cardInfo)
        }
    }

    private fun cardInfo(player: Player): List<CardInfo> {
        return player.hands.cardList().map {
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

    private fun showFinalResult(blackJackGame: BlackJackGame) {
        OutputConsole.printNewLine()
        val players = blackJackGame.allPlayers()
        players.forEach { player ->
            val cardInfo = cardInfo(player)
            val resultScore = player.handsCardScore()
            OutputConsole.printCardWithResult(playerName = player.name, cardInfo = cardInfo, result = resultScore.toString())
        }
    }
}
