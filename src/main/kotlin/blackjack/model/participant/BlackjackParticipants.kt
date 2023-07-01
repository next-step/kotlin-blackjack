package blackjack.model.participant

import blackjack.model.BlackjackPlayersCardCountConsumer

data class BlackjackParticipants(
    val participants: Collection<BlackjackParticipant>,
) {
    init {
        participants.forEach(BlackjackParticipant::draw)
    }

    companion object {
        fun withDealerAndDraw(
            players: Collection<BlackjackPlayer>,
            dealer: BlackjackDealer,
            countConsumer: BlackjackPlayersCardCountConsumer,
        ): BlackjackParticipants {
            countConsumer.consumePlayersCardCount(dealer, players, BlackjackParticipant.INITIAL_DEALING_COUNT)
            return BlackjackParticipants(players + dealer)
        }
    }
}
