package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.state.Start

class Player(
    name: ParticipantName,
    firstCard: Card,
    secondCard: Card,
): Participant(
    name = name,
    state = Start.handCard(firstCard, secondCard)
) {

    fun handCard(card: Card) {
        cards().add(card)
    }

    fun isBust(): Boolean {
        return cards().isBust()
    }

    fun calculateScore(): Score {
        return cards().calculateScore()
    }
}
