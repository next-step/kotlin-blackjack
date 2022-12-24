package blackjack.domain

data class BlackJackGame(
    val deck: Deck,
    var dealer: Dealer = Dealer(),
    var players: Players = Players(),
) {
    fun drawInitCards() {
        deck.drawInitCards().values.forEach {
            dealer.hit(it)
        }
        players = players.drawInitCards(deck)
    }

    fun getPlayerResults(): List<PlayerResult> {
        return players.calculateResult(dealer)
    }

    fun playDealer(
        printDealerHitOrStay: (Boolean) -> Unit
    ) {
        var isDrawn = false

        if (dealer.isHit()) {
            dealer.hit(deck.draw())
            isDrawn = true
        }
        printDealerHitOrStay(isDrawn)
    }
    fun play(
        inputIsGetCard: (Player) -> Boolean,
        printPlayerStatus: (Player) -> Unit
    ) {
        players.values.forEach {
            while (it.isHit() && inputIsGetCard(it)) {
                it.hit(deck.draw())
                printPlayerStatus(it)
            }
        }
    }
}
