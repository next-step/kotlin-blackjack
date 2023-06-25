package blackjack.controller

import blackjack.domain.model.Player
import blackjack.domain.model.PlayerInfo
import blackjack.domain.model.Round
import blackjack.util.InputParser
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {

    fun execute(round: Round) {
        val players = inputName(round)
        divideCards(players)
        players.forEach {
            playerTurn(it, round)
        }
        result(players)
    }

    private fun inputName(round: Round): List<Player> {
        val names = InputParser.parse(InputView.inputNames())
        val players = mutableListOf<Player>().apply {
            addAll(names.map { Player(round, PlayerInfo(it)) })
        }
        return players
    }

    private fun divideCards(players: List<Player>) {
        OutputView.divideCard(players.map { it.info.name })
        OutputView.printPlayersCards(players)
    }

    private fun playerTurn(player: Player, round: Round) {
        while (player.canGetCard() && InputView.inputCard(player.info.name)) {
            player.addCard(round.getCard())
            OutputView.printCards(player)
        }
    }

    private fun result(players: List<Player>) {
        OutputView.printPlayersCards(players, isResult = true)
    }
}
