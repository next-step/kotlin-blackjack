package blackjack.entity

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun initializeHands(deck: Deck) {
        players.forEach { player ->
            repeat(2) { player.receiveCard(deck.deal()) }
        }
    }
}
