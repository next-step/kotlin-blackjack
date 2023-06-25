package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Game
import blackjack.domain.model.Player
import blackjack.domain.model.PlayerInfo
import blackjack.util.InputParser
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {

    fun execute(game: Game) {
        val dealer = Dealer(game)
        val players = inputName(game)
        val users = mutableListOf<Player>(dealer).apply {
            addAll(players)
        }
        divideCards(users)
        players.forEach {
            playerTurn(it, game)
        }
        dealerTurn(dealer, game)
        result(users)
    }

    private fun inputName(game: Game): List<Player> {
        val names = InputParser.parse(InputView.inputNames())
        val players = mutableListOf<Player>().apply {
            addAll(names.map { Player(game, PlayerInfo(it)) })
        }
        return players
    }

    private fun divideCards(players: List<Player>) {
        OutputView.divideCard(players.map { it.info.name })
        OutputView.printPlayersCards(players)
    }

    private fun dealerTurn(dealer: Dealer, game: Game) {
        while (dealer.canGetCard()) {
            dealer.addCard(game.getCard())
            OutputView.printDealerGetCard()
        }
    }

    private fun playerTurn(player: Player, game: Game) {
        while (player.canGetCard() && InputView.inputCard(player.info.name)) {
            player.addCard(game.getCard())
            OutputView.printCards(player)
        }
    }

    private fun result(players: List<Player>) {
        OutputView.printPlayersCards(players, isResult = true)
    }
}
