package blackjack.domain

class Game(
    val players: List<Player>,
    private val deck: Deck,
) {
    fun start() {
        players.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }
    }
}
