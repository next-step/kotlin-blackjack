package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.state.Started

class Player(
    name: ParticipantName,
    firstCard: Card,
    secondCard: Card,
): Participant(
    name = name,
    state = Started.handCard(firstCard, secondCard)
) {

    fun isBust(): Boolean {
        return cards().isBust()
    }
}
