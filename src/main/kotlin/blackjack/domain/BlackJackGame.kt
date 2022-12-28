package blackjack.domain

data class BlackJackGame(
    val deck: Deck,
    var dealer: Dealer,
    var users: Users,
) {
    fun drawInitCards() {
        deck.drawInitCards().values.forEach {
            dealer.hit(it)
        }
        users = users.drawInitCards(deck)
    }

    fun getPlayerResults(): List<PlayerResult> {
        val playerResults = users.calculateResult(dealer)
        calculateDealerProfit(playerResults)

        return playerResults
    }

    private fun calculateDealerProfit(playerResults: List<PlayerResult>) {
        dealer = dealer.calculateProfit(playerResults)
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
    fun playPlayers(
        inputIsGetCard: (Player) -> Boolean,
        printPlayerStatus: (Player) -> Unit
    ) {
        users.values.forEach {
            while (it.isHit() && inputIsGetCard(it)) {
                it.hit(deck.draw())
                printPlayerStatus(it)
            }
        }
    }
}
