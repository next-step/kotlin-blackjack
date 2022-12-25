package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.player.Player
import blackjack.infra.io.BlackjackInputReader
import blackjack.view.BlackjackView
import blackjack.view.PlayerInputView
import blackjack.view.PlayerView

class BlackjackController(
    private val playerInputView: PlayerInputView = PlayerInputView(),
    private val blackjackView: BlackjackView = BlackjackView(),
    private val playerView: PlayerView = PlayerView(),
    private val blackjackInputReader: BlackjackInputReader = BlackjackInputReader()
) {
    fun run() {
        playerInputView.printlnPlayerInputPrompt()

        val playerNames = blackjackInputReader.readPlayerNames()

        val blackjack = Blackjack.of(playerNames)
        val players = blackjack.players

        blackjackView.printlnReadyToStart(blackjack)

        players.forEach {
            playerView.printlnPlayer(it)
        }

        blackjackView.printNewLine()

        players.filter { it.isNotFinished() }
            .forEach { player ->
                playerView.printlnHitYn(player)
                askPlayerChoice(blackjack, player)
            }

        blackjackView.printNewLine()

        players.forEach {
            playerView.printlnPlayerResult(it)
        }
    }

    private fun askPlayerChoice(blackjack: Blackjack, player: Player) {
        while (player.isNotFinished()) {
            val playerHitYn = blackjackInputReader.readPlayerHitYn()
            hitOrStay(playerHitYn, blackjack, player)
            playerView.printlnPlayer(player)
        }
    }

    private fun hitOrStay(playerHitYn: String, blackjack: Blackjack, player: Player) {
        when (playerHitYn) {
            "y", "Y" -> blackjack.giveCardTo(player)
            "n", "N" -> blackjack.acceptStayFrom(player)
            else -> throw IllegalArgumentException("should input y or n [$playerHitYn]")
        }
    }
}
