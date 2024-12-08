package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackGameResult
import blackjack.domain.BlackJackGameResults
import blackjack.domain.Dealer
import blackjack.domain.DrawParticipant
import blackjack.domain.DrawResult
import blackjack.domain.Player
import blackjack.domain.ParticipantName

class GameController {
    private lateinit var blackJackGame: BlackJackGame

    fun initial(inputPlayerNames: String?): List<DrawResult> {
        require(!this::blackJackGame.isInitialized) { "이미 게임이 시작되었습니다." }

        val players = inputPlayerNames
            ?.split(",")
            ?.map { Player(participantName = ParticipantName(it)) }
            ?: throw IllegalArgumentException("게임에 참여할 사람의 이름은 필수 입니다.")

        val participants = listOf(Dealer()) + players
        blackJackGame = BlackJackGame(participants)
        return blackJackGame.initialDraw()
    }

    fun canDrawForAllPlayers(): Boolean {
        validateInitialized()
        return blackJackGame.canDrawForAllPlayers()
    }

    fun findDrawParticipant(): DrawParticipant? {
        validateInitialized()
        val drawParticipant = blackJackGame.findDrawPlayer()
        return drawParticipant?.let {
            DrawParticipant(
                name = it.name,
                dealer = drawParticipant.isDealer(),
            )
        }
    }

    fun draw(
        inputplayerName: String,
        inputNeedDraw: String?,
    ): DrawResult {
        validateInitialized()

        val playerName = ParticipantName(inputplayerName)
        val needDraw = !(inputNeedDraw.isNullOrBlank() || inputNeedDraw == "n")

        return if (needDraw) {
            blackJackGame.drawCard(playerName)
        } else {
            blackJackGame.stopDraw(playerName)
        }
    }

    fun result(): BlackJackGameResults {
        validateInitialized()
        return blackJackGame.result()
    }

    private fun validateInitialized() {
        require(this::blackJackGame.isInitialized) { "게임이 시작되지 않았습니다." }
    }
}
