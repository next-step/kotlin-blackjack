package blackjack.domain

data class BlackJackGame(
    val deck: Deck,
    var dealer: Dealer = Dealer(),
    var players: Players = Players(),
) {
    private var isDealerDrawn: Boolean = false

    fun drawInitCards() {
        deck.drawInitCards().values.forEach {
            dealer.hit(it)
        }
        players = players.drawInitCards(deck)
    }

    fun getPlayerResults(): List<PlayerResult> {
        return players.calculateResult(dealer)
    }

    fun playDealer() {
        val isHit = dealer.isHit()
        if (isHit) dealer.hit(deck.draw())
        isDealerDrawn = true
    }

    fun isDealerDrawn() = isDealerDrawn
}
