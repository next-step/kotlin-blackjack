package blackjack.domain

class Dealer(name: String) : Participant(name) {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
        if (cards.toScore() > Score.DEALER_STAND_THRESHOLD) turnStand()
    }
}
