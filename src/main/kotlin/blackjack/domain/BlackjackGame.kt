package blackjack.domain

class BlackjackGame(
    val dealer: Dealer = Dealer(),
    val participants: Participants
) {
    fun betParticipant(getBettingMoney: (Participant) -> Int) {
        participants.forEach { participant ->
            participant.bet(getBettingMoney(participant))
        }
    }

    fun receiveFirstTurnCard(blackjackShoe: BlackjackShoe) {
        dealer.receiveFirstTurnCard(blackjackShoe = blackjackShoe)
        participants.receiveFirstTurnCard(blackjackShoe = blackjackShoe)
    }

    fun drawCardOfPlayer(
        blackjackShoe: BlackjackShoe,
        getMoreCard: (Participant) -> Boolean,
        onReceivedCard: (Participant) -> Unit
    ) {
        participants.forEach { participant ->
            while (participant.canReceiveCard && getMoreCard(participant)) {
                val card = blackjackShoe.draw()
                participant.receiveCard(card)
                onReceivedCard(participant)
            }
        }
    }

    fun drawCardOfDealer(
        blackjackShoe: BlackjackShoe,
        onReceivedCard: () -> Unit
    ) {
        while (dealer.canReceiveCard) {
            val card = blackjackShoe.draw()
            dealer.receiveCard(card)
            onReceivedCard()
        }
    }
}
