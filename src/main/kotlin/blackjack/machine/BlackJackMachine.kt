package blackjack.machine

import blackjack.deck.Deck
import blackjack.player.Player
import blackjack.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine(
    private val deck: Deck,
) {
    fun play() {
        val playerList =
            Players
                .generateFromNames(InputView.inputPlayerNames())
                .players
                .map { player ->
                    (1..DEFAULT_HAND_SIZE).fold(player) { updatedPlayer, _ ->
                        updatedPlayer.hitCard(deck.draw())
                    }
                }
        var players = Players(players = playerList)

        ResultView.printPlayersName(players = players)
        ResultView.printPlayersCardStatus(players = players)

        while (isGameActive(players = players)) {
            players = Players(players = players.players.map { playTurn(it) })
            ResultView.printPlayersCardStatusAndSum(players = players)
        }
    }

    private fun playTurn(player: Player): Player =
        when {
            player.isBust() -> player
            !InputView.isHitCard(player) ->
                player
                    .also { ResultView.printPlayerCard(it) }
            else ->
                player
                    .hitCard(deck.draw())
                    .also { ResultView.printPlayerCard(it) }
        }

    private fun isGameActive(players: Players) = players.players.any { !it.isBust() }

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
    }
}
