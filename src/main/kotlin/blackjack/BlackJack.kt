package blackjack

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView


class BlackJack {
    fun start() {
        val players = Players(InputView.getPlayerName())

        OutputView.renderPlayers(players.playerList)

        players.playerList.forEach {
            playGameWithEachPlayer(it) { player -> OutputView.renderPlayer(player, ::println) }
        }

        OutputView.renderResult(getGameResults(players.playerList))
    }

    private fun getGameResults(playerList: List<Player>) = playerList.map {
        GameResult(it, ResultDealer.getTotalScore(it.cardDeck))
    }

    private fun playGameWithEachPlayer(player: Player, render: (Player) -> Unit) {
        while (getMoreCardOrNot(player.name)) {
            player.cardDeck.addCard(CardDealer.getCard())
            render(player)
        }
    }

    private fun getMoreCardOrNot(name: String) = when (InputView.askGetCardMore(name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
