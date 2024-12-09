package blackjack.domain

class BlackJackGame(
    participants: List<Participant>,
) {
    private val participants = Participants(participants)
    private val deck = Deck.randomCardDeck()

    fun initialDraw(): List<DrawResult> {
        repeat(2) { _ ->
            participants.readAll().forEach { it.addCard(deck.draw()) }
        }

        return participants.readAll()
            .map {
                DrawResult(
                    dealer = it.isDealer(),
                    playerName = it.name.value,
                    cards = it.currentCards,
                )
            }
    }

    fun canDrawForAllPlayers(): Boolean = participants.canDrawForAllPlayers()

    fun findDrawPlayer(): Participant? = participants.findDrawPlayer()

    fun drawCard(participantName: ParticipantName): DrawResult {
        val participant = participants.findByName(participantName)
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        participant.addCard(deck.draw())
        return DrawResult(
            dealer = participant.isDealer(),
            playerName = participantName.value,
            cards = participant.currentCards,
        )
    }

    fun stopDraw(participantName: ParticipantName): DrawResult {
        val participant = participants.findByName(participantName)
        participant
            ?.stopDraw()
            ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        return DrawResult(
            dealer = participant.isDealer(),
            playerName = participantName.value,
            cards = participant.currentCards,
        )
    }

    fun result(): BlackJackGameResults {
        check(participants.isAllPlayerStopDraw()) { "모든 플레이어의 턴이 종료되지 않았습니다."}

        val blackJackGameResults = participants.readAll()
            .map {
                BlackJackGameResult(
                    dealer = it.isDealer(),
                    playerName = it.name.value,
                    cards = it.currentCards,
                    totalValue = it.totalCardScore(),
                )
            }
        return BlackJackGameResults(blackJackGameResults)
    }
}

data class DrawResult(
    val dealer: Boolean,
    val playerName: String,
    val cards: List<DrawCard>,
)
