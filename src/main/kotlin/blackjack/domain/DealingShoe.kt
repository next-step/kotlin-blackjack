package blackjack.domain

class DealingShoe(private val deck: Deck = Deck()) {
    fun dealTwoCardsEach(participants: Participants) {
        participants.receiveTwoCardsEach(deck)
    }

    fun dealCard(participant: Participant) {
        participant.receive(deck.draw())
    }
}
