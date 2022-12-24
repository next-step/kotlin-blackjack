package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.infra.io.BlackjackInputReader
import blackjack.view.BlackjackView
import blackjack.view.PlayerInputView
import blackjack.view.PlayerView

class BlackjackController(
    private val playerInputView: PlayerInputView = PlayerInputView(),
    private val playerView: PlayerView = PlayerView(),
    private val blackjackView: BlackjackView = BlackjackView(playerView),
    private val blackjackInputReader: BlackjackInputReader = BlackjackInputReader()
) {
    fun run() {
        playerInputView.printlnPlayerInputPrompt()

        val playerNames = blackjackInputReader.readPlayerNames()

        val blackjack = Blackjack.of(playerNames)

        blackjackView.printlnBeforeStart(blackjack)

//        blackjack.players.forEach { playerView.printlnPlayer(it) }

        // TODO: hit & stay

        blackjackView.printlnResult(blackjack)
    }
}
