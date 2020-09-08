package blackJack.domain

class BlackJack(val players: Players) {
    val dealer = Dealer()

    init {
        readyGame()
    }

    private fun readyGame() {
        repeat(2) { giveCardAllPerson() }
    }

    private fun giveCardAllPerson() {
        players.players.forEach { dealer.giveCard(it) }
        dealer.giveCard(dealer)
    }

    fun bettingPlayers(bettingMoney: (player: Player) -> Unit) {
        players.forEach { bettingMoney(it) }
    }

    fun giveCard(isHit: Boolean, player: Player) {
        if (isHit) {
            dealer.giveCard(player)
        }
    }

    fun getResult(): Result {
        return Result(players, dealer)
    }
}
