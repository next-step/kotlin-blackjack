package interfaces

import application.BlackJackGame
import common.Executable
import domain.player.Participants
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

    private fun queryParticipants(): Participants {
        val nameList = InputConsole.queryParticipantName()
        return Participants.from(names = nameList)
    }

    private fun initGame(blackJackGame: BlackJackGame) {
        blackJackGame.init()
        val allPlayers = blackJackGame.allPlayers()
        OutputConsole.printInit(allPlayers = allPlayers)
    }

    private fun showAllParticipantsHands(blackJackGame: BlackJackGame) {
        val dealer = blackJackGame.dealer
        OutputConsole.printDealerCard(dealer = dealer)

        val players = blackJackGame.allPlayers()
        OutputConsole.printPlayerCard(players)
    }

    private fun queryReceiveCard(blackJackGame: BlackJackGame) {
        val allPlayers = blackJackGame.allPlayers()
        allPlayers.forEach {
            blackJackGame.playsTurn(player = it) { player -> InputConsole.queryReceiveCard(player.name) }
            OutputConsole.printCard(it)
        }
    }

    private fun dealerReceiveCard(blackJackGame: BlackJackGame) {
        val isReceiveCard = blackJackGame.tryDealerReceiveCard()
        if (isReceiveCard) {
            OutputConsole.printDealerMoreReceiveCard()
        }
    }

    private fun showFinalResult(blackJackGame: BlackJackGame) {
        val dealer = blackJackGame.dealer
        OutputConsole.printCardWithDealerResult(dealer = dealer)

        val players = blackJackGame.allPlayers()
        OutputConsole.printCardWithResult(players)

        val gameResult = blackJackGame.complete()
        val resultPlayerBoards = gameResult.resultPlayerBoards
        val resultDealerBoard = gameResult.resultDealerBoard
        OutputConsole.printFinalResult(resultDealerBoard, resultPlayerBoards)
    }
}
