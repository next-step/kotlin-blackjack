package interfaces

import application.BlackJackGame
import common.Executable
import domain.Participants
import domain.Player
import interfaces.ui.InputConsole
import interfaces.ui.OutputConsole

class BlackJackController : Executable {

    override fun execute() {

        val participants = queryParticipants()
        val blackJackGame = BlackJackGame(players = participants)

        init(blackJackGame)
        showAllParticipantsHands(blackJackGame)

        queryReceiveCard(blackJackGame)

        showFinalResult(blackJackGame)
    }

    private fun init(blackJackGame: BlackJackGame) {
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

    private fun cardInfo(player: Player): String {
        return player.hands.cardList().joinToString(", ") {
            val number = it.number.nameValue
            val name = it.shape.nameValue
            "${number}${name}"
        }
    }

    private fun queryReceiveCard(blackJackGame: BlackJackGame) {
        OutputConsole.printNewLine()
        val players = blackJackGame.allPlayers()
        players.forEach { player ->
            receiveCard(player = player, blackJackGame = blackJackGame)
        }
    }

    private fun receiveCard(player: Player, blackJackGame: BlackJackGame) {
        var isReceive = InputConsole.queryReceiveCard(player.name)
        while (isReceive) {
            blackJackGame.receiveCard(player)
            val cardInfo = cardInfo(player)
            OutputConsole.printCard(playerName = player.name, cardInfo = cardInfo)
            isReceive = InputConsole.queryReceiveCard(player.name)
        }
        if (!isReceive) {
            val cardInfo = cardInfo(player)
            OutputConsole.printCard(playerName = player.name, cardInfo = cardInfo)
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
