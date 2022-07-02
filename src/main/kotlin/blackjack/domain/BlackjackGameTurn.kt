package blackjack.domain

class BlackjackGameTurn private constructor(val participant: Participant) {
    fun isTurnEnd(): Boolean = !participant.isReceivable()

    fun hit(playingCards: PlayingCards) {
        participant.receive(playingCards)
    }

    fun stay() {
        (participant as? Player)?.stay()
    }

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
