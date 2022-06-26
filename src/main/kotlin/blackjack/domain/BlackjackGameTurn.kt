package blackjack.domain

@JvmInline
value class BlackjackGameTurn private constructor(val participant: Participant) {
    fun isTurnEnd(): Boolean = !participant.isReceivable()

    companion object {
        fun from(participants: Participants): BlackjackGameTurn {
            val participant = participants.players.find { player ->
                player.isReceivable()
            } ?: participants.dealer

            return BlackjackGameTurn(participant)
        }

        fun from(participant: Participant): BlackjackGameTurn {
            return BlackjackGameTurn(participant)
        }
    }
}
