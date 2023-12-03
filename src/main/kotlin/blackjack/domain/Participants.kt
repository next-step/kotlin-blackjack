package blackjack.domain

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    val value: List<Participant> = listOf(dealer) + players

    fun drawInitialCards() {
        value.forEach {
            it.receiveCard(Deck.draw())
            it.receiveCard(Deck.draw())
        }
    }
}
