package blackjack.domain

class Dealer(val name: String) : Participant() {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
        if (state.scoring().value > Score.DEALER_STAND_THRESHOLD) turnStand()
    }
}
