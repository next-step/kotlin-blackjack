package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackGameResult
import blackjack.domain.DrawResult
import blackjack.domain.Player
import blackjack.domain.PlayerName

class GameController {
    private lateinit var blackJackGame: BlackJackGame

    fun initial(inputPlayers: String?): List<DrawResult> {
        require(!this::blackJackGame.isInitialized) { "이미 게임이 시작되었습니다." }

        val players = inputPlayers
            ?.split(",")
            ?.map { Player(PlayerName(it)) }
            ?: throw IllegalArgumentException("게임에 참여할 사람의 이름은 필수 입니다.")

        blackJackGame = BlackJackGame(players)
        return blackJackGame.initialDraw()
    }

    fun canDrawForAllPlayers(): Boolean {
        validateInitialized()
        return blackJackGame.canDrawForAllPlayers()
    }

    fun findDrawPlayer(): PlayerName {
        validateInitialized()
        return blackJackGame.findDrawPlayer()
    }

    fun draw(
        inputplayerName: String,
        inputNeedDraw: String?,
    ): DrawResult {
        validateInitialized()

        val playerName = PlayerName(inputplayerName)
        val needDraw = !(inputNeedDraw.isNullOrBlank() || inputNeedDraw == "n")

        return if (needDraw) {
            blackJackGame.drawCard(playerName)
        } else {
            blackJackGame.stopDraw(playerName)
        }
    }

    fun result(): List<BlackJackGameResult> {
        validateInitialized()
        return blackJackGame.result()
    }

    private fun validateInitialized() {
        require(this::blackJackGame.isInitialized) { "게임이 시작되지 않았습니다." }
    }
}
