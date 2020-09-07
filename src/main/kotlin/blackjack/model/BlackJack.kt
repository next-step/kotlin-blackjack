package blackjack.model

class BlackJack(
    val players: Players,
    val dealer: Dealer,
    private val cardDeck: CardDeck = CardDeck().apply { this.shuffle() }
) {

    fun firstTurn() {
        repeat(players.size()) {
            players[it].draw(cardDeck.drawCard(), cardDeck.drawCard())
        }
    }

    fun race(player: Player) {
        players.race(player, cardDeck)
    }

    fun dealerDrawCheck(): Boolean {
        if (!dealer.isOver17()) {
            dealer.draw(cardDeck.drawCard())
            return true
        }
        return false
    }

    fun gameResult(dealer: Dealer) {
        return players.calculateResult(dealer)
    }
}
