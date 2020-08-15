package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.card.CardDeck
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGameController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView,
    private val players: Players = Players(inputView.getNames()),
    private val cardDeck: CardDeck = CardDeck(),
    private val blackJackGame: BlackJackGame = BlackJackGame(players, cardDeck)
) {

    fun startGame() {
        gameBatting()
        playsTurn()
        showResult()
    }

    private fun gameBatting() {
        blackJackGame.gameBatting()
        resultView.showPlayers(blackJackGame.getPlayersStatus())
    }

    private fun playsTurn() {
        blackJackGame.playsTurn().forEach { player ->
            playerOneMore(player)
        }
    }

    private fun playerOneMore(player: Player) {
        while (player.canHit() && inputView.getIsOneMore(player)) {
            blackJackGame.playHit(player)
            resultView.showPlayer(player)
        }
    }

    private fun showResult() {
        resultView.showResult(blackJackGame.getPlayersStatus())
    }
}

fun main() {
    BlackJackGameController().startGame()
}
