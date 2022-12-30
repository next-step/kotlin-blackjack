package blackjack.domain.participant.state.result

import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Role

sealed class Result {
    object Win : Result()
    object Lose : Result()
    object Draw : Result()

    override fun toString(): String {
        return when (this) {
            is Win -> "승"
            is Lose -> "패"
            is Draw -> "무"
        }
    }

    companion object {
        fun calculateResult(participants: Participants): Map<Role, Result> {
            if (participants.getDealer().isBlackjack()) {
                return participants.getPlayers().associateWith { Result.Lose }
            }

            if (participants.getDealer().isBust()) {
                return participants.getPlayers().associateWith { Result.Win }
            }
            return participants.getPlayers().associateWith { it.calculateResult(participants.getDealer().getScore()) }
        }
    }
}
