package blackjack.domain

class Game(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    val status: GameStatus
        get() = GameStatus(dealer, players)

    fun processPlayers(interaction: PlayerInteraction) {
        players.forEach { processPlayer(it, interaction) }
    }

    fun processDealer(): Int {
        var drawCount = 0

        while (dealer.shouldDraw) {
            dealer.drawSelf()
            drawCount++
        }

        return drawCount
    }

    private tailrec fun processPlayer(player: Player, interaction: PlayerInteraction) {
        if (!player.canDrawCard) {
            return
        }

        val shouldGiveCard = interaction.getDrawChoice(player)
        if (shouldGiveCard) {
            dealer.giveCard(player)
            interaction.printStatus(player)
            processPlayer(player, interaction)
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
