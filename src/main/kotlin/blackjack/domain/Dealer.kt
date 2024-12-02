package blackjack.domain

class Dealer(private val deck: Deck) {
    fun hit(player: Player) {
        player.receiveCard(deck.draw())
    }
}
