package blackJack.domain

class BlackJack(val players: Players) {
    val dealer = Dealer()

    fun readyGame() {
        repeat(2) { giveCardAllPerson() }
    }

    private fun giveCardAllPerson() {
        players.players.forEach { dealer.giveCard(it) }
        dealer.giveCard(dealer)
    }

    fun bettingMoney(getBetMoney: (player: Player) -> Int) {
        players.players.forEach { it.bettingMoney(getBetMoney(it)) }
    }

    fun getDealerCard(resultViewDealer: (dealer: Dealer) -> Unit) {
        while (!dealer.isBust() && dealer.canAddCard()) {
            dealer.giveCard(dealer)
            resultViewDealer(dealer)
        }
    }

    fun getResult(): Result {
        return Result(players, dealer)
    }

    fun hitOrStay(whileNotBust: (player: Player) -> Unit) {
        players.players.forEach { whileNotBust(it) }
    }
}
