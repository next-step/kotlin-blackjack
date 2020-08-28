package blackjack.model

class BlackJack(val players: Players, private val cardDeck: CardDeck = CardDeck().apply { this.shuffle() }) {

    fun firstTurn() {
        repeat(players.size()) {
            players[it].draw(cardDeck.drawCard(), cardDeck.drawCard())
        }
    }

    fun race(player: Player) {
        players.race(player, cardDeck)
    }

    fun dealerDrawCheck(): Boolean {
        if (DRAW_CONDITION > players.dealer.score()) {
            players.dealer.draw(cardDeck.drawCard())
            return true
        }
        return false
    }

    fun gameResult(dealer: Dealer) {
        return players.calculateResult(dealer)
    }
}
