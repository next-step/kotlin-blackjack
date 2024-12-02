package blackjack.machine

import blackjack.deck.Deck
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
        val players = Players(players = playerList)

        ResultView.printPlayersName(players = players)
        ResultView.printPlayersCardStatus(players = players)
    }

    companion object {
        private const val DEFAULT_HAND_SIZE = 2
        const val BLACKJACK = 21
    }
}
