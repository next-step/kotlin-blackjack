package blackjack.domain

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    val value: List<Participant> = listOf(dealer) + players

    fun drawInitialCards(deck: Deck) {
        value.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }
    }
}
