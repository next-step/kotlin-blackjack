package blackjack.domain

class BlackJackGame(
    private val participants: List<Participant>,
) {
    private val drawSupporter = DrawSupporter()

    fun initialDraw(): List<DrawResult> {
        repeat(2) { _ ->
            participants.forEach { it.addCard(drawSupporter.drawCard()) }
        }

        return participants
            .map {
                DrawResult(
                    dealer = it.isDealer(),
                    playerName = it.name.value,
                    cards = it.currentCards,
                )
            }
    }

    fun canDrawForAllPlayers(): Boolean = participants.any { it.canDraw() }

    fun findDrawPlayer(): Participant? {
        val startOrder = drawSupporter.currentDrawOrder
        val targetOrder = (startOrder..< participants.size)
            .firstOrNull { participants[it].canDraw() }

        if (targetOrder == null) {
            return null
        }

        drawSupporter.incrementDrawOrder(targetOrder, participants.size)
        return participants[targetOrder]
    }

    fun drawCard(playerName: ParticipantName): DrawResult {
        val participant = participants.find { it.name == playerName }
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        participant.addCard(drawSupporter.drawCard())
        return DrawResult(
            dealer = participant.isDealer(),
            playerName = playerName.value,
            cards = participant.currentCards,
        )
    }

    fun stopDraw(playerName: ParticipantName): DrawResult {
        val participant = participants.find { it.name == playerName }
        participant
            ?.stopDraw()
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        return DrawResult(
            dealer = participant.isDealer(),
            playerName = playerName.value,
            cards = participant.currentCards,
        )
    }

    fun result(): BlackJackGameResults {
        check(isAllPlayerStopDraw()) { "모든 플레이어의 턴이 종료되지 않았습니다."}

        val blackJackGameResults = participants.map {
            BlackJackGameResult(
                dealer = it.isDealer(),
                playerName = it.name.value,
                cards = it.currentCards,
                totalValue = it.totalCardScore(),
            )
        }
        return BlackJackGameResults(blackJackGameResults)
    }

    private fun isAllPlayerStopDraw(): Boolean = participants.all { !it.canDraw() }
}

data class DrawResult(
    val dealer: Boolean,
    val playerName: String,
    val cards: List<DrawCard>,
)
