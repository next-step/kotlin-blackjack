package blackjack.domain.participant.vo

import blackjack.domain.Score
import blackjack.domain.participant.type.Status

data class ParticipantInformation(
    val name: Name,
    val status: Status
) {
    fun isStay(): Boolean = status == Status.STAY

    fun isBust(): Boolean = status == Status.BUST

    fun isPlay(): Boolean = status == Status.PLAY

    fun changeStatus(score: Score): ParticipantInformation = when {
        score == Score.BLACKJACK -> copy(status = Status.BLACKJACK)
        score < Score.BLACKJACK -> copy(status = Status.PLAY)
        score > Score.BLACKJACK -> copy(status = Status.BUST)
        else -> this
    }

    fun stay(): ParticipantInformation = copy(status = Status.STAY)
}
