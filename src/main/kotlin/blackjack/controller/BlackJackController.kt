package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.util.onEndPlay
import blackjack.util.onPreparePlay
import blackjack.util.onStartPlay
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackController {
    fun play() {
        val isDrawCard: (String) -> Boolean = { name ->
            val drawMoreCardYesOrNo = InputView.readIsDrawMore(name)
            drawMoreCardYesOrNo
        }

        initPlayers(
            fetchNames = { InputView.readPlayerNames() },
            onPlayerInit = { names -> ResultView.printPlayerInitMessage(names) },
        )
            .onPreparePlay { player -> ResultView.printPlayerNameWithCards(player) }
            .onStartPlay { player ->
                player.play(
                    isDrawCard = isDrawCard,
                    onDrawCard = { ResultView.printPlayerNameWithCards(player) },
                    onExitPlay = { ResultView.printPlayerNameWithCards(player) },
                )
            }.onEndPlay { player -> ResultView.printFinalScores(player) }
    }

    private fun initPlayers(
        fetchNames: () -> List<String>,
        onPlayerInit: (List<String>) -> Unit,
    ): List<Player> {
        val names = fetchNames()
        val deck = Deck()
        val players = names.map { Player(it, deck) }
        onPlayerInit(names)
        return players
    }
}
