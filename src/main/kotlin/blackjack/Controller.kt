package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {
    fun playBlackJackGame() {
        val players = getPlayers()
        val game = BlackJackGame(players = players)
        startGame(game)
        playGame(game)
        endGame(game.getParticipants())
    }

    private fun getPlayers(): Players {
        val playerNames = InputView.getPlayerNames()
        return Players.of(playerNames)
    }

    private fun startGame(game: BlackJackGame) {
        game.start()
        OutputView.printStart(game.getParticipants())
    }

    private fun playGame(game: BlackJackGame) {
        game.playerPlay(
            isHit = { InputView.isHit(it.name) },
            afterDrawCard = { name, cards -> OutputView.printCards(name, cards) }
        )
        game.dealerPlay { name, cards -> OutputView.printCards(name, cards) }
    }

    private fun endGame(participants: List<Participant>) {
        for (participant in participants) {
            OutputView.printPlayerResult(participant)
        }
    }
}

fun main() {
    val controller = Controller()
    controller.playBlackJackGame()
}
