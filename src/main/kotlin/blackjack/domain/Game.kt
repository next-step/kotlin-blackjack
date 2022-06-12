package blackjack.domain

class Game private constructor(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    val status: GameStatus
        get() = GameStatus(dealer, players)

    fun processPlayers(drawChoice: (player: Player) -> Boolean) {
        players.forEach { processPlayer(it, drawChoice) }
    }

    fun processDealer(): Int {
        var drawCount = 0

        while (dealer.shouldDraw) {
            dealer.drawSelf()
            drawCount++
        }

        return drawCount
    }

    private tailrec fun processPlayer(player: Player, drawChoice: (player: Player) -> Boolean) {
        if (!player.canDrawCard) {
            return
        }

        val shouldGiveCard = drawChoice(player)
        if (shouldGiveCard) {
            dealer.giveCard(player)
            processPlayer(player, drawChoice)
        }
    }

    companion object {
        fun start(playerNames: List<String>): Game {
            val deck = Deck.shuffled()
            val dealer = Dealer(deck)
            val players = dealer.startGame(playerNames)

            return Game(dealer, players)
        }
    }
}
