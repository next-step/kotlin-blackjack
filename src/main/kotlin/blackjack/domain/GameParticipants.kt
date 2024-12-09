package blackjack.domain

class GameParticipants(
    val dealer: Dealer,
    val players: Players,
) {
    fun distributeInitialCards(deck: Deck) {
        players.distributeInitialCards(deck)
        dealer.distributeInitialCards(deck)
    }
}
