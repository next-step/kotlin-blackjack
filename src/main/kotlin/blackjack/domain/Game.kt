package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck = Deck()
) {
    fun getDealer(): Dealer = Dealer().initialCard(deck)

    fun initialCard(): Players {
        return players.initialCard(deck)
    }

    fun playDealer(dealer: Dealer): Dealer {
        return if (dealer.canHit()) {
            dealer.hit(deck)
        } else {
            dealer
        }
    }

    fun playPlayers(
        players: Players,
        inputScratch: (player: Player) -> Boolean,
        printPlayerCards: (player: Player) -> Unit
    ): Players {
        val result = players.list.map {
            playPlayer(it, inputScratch, printPlayerCards)
        }
        println()
        return Players(result)
    }

    private tailrec fun playPlayer(
        player: Player,
        inputScratch: (player: Player) -> Boolean,
        printPlayerCards: (player: Player) -> Unit
    ): Player {

        if (!player.canHit() || !inputScratch(player)) {
            return player
        }

        val result = player.hit(deck)
        printPlayerCards(result)
        return playPlayer(result, inputScratch, printPlayerCards)
    }

    fun finish(dealer: Dealer, players: Players): List<PlayerResult> {
        val playerResults = PlayerResult.ofGamePlayers(dealer, players)
        val dealerProfit = Profit.ofDealer(playerResults)
        return listOf(PlayerResult(dealer, dealerProfit)) + playerResults
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2

        fun getScore(player: Player): Int {
            return if (player.isBurst()) 0 else player.countingCard()
        }
    }
}
