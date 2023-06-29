package blackjack.model

import blackjack.model.participant.BlackjackParticipant

fun interface BlackjackPlayerConsumer {

    fun consumeParticipant(participant: BlackjackParticipant)
}
