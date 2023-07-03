package blackjack.domain.player

import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerHands
import blackjack.domain.card.Card

@JvmInline
value class Participants(
    val values: List<Participant>
) {
    init {
        require(values.isNotEmpty()) { "참여자은 최소 1명 이상이 있어야 합니다." }
    }

    fun drawAllParticipants(drawAction: () -> Card) {
        values.forEach { it.draw(drawAction()) }
    }

    fun hands(): List<PlayerHands> = values.map { PlayerHands.from(it) }

    fun participantName(index: Int): String = participant(index).name()

    fun participantDraw(index: Int, drawAction: () -> Card): Pair<PlayerHands, Boolean> {
        val participant = participant(index)
        participant.draw(drawAction())
        return PlayerHands.from(participant) to participant.isFinished()
    }

    fun participantStay(index: Int) {
        participant(index).stay()
    }

    fun size(): Int = values.size

    fun competeWith(participant: Participant): List<PlayerGameResult> =
        values.map { PlayerGameResult.of(it, it.competeWith(participant)) }

    private fun participant(index: Int): Participant {
        require(index in values.indices) { "해당 위치의 참여자가 없습니다." }
        return values[index]
    }
}
