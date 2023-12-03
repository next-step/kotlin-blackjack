package blackjack.domain

class Participants(
    dealer: Dealer,
    players: List<Player>,
) {
    val value: List<Participant> = listOf(dealer) + players

    fun drawInitialCards() {
        value.forEach {
            it.receiveCard(Deck.draw())
            it.receiveCard(Deck.draw())
        }
    }
}
