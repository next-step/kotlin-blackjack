package blackjack.business

class Players private constructor(allPlayers: List<Player>) {

    private val _players: List<Player> = allPlayers

    private val allPlayers: List<Player>
        get() = _players.toList()

    fun forEachPlayer(action: (Player) -> Unit) = allPlayers.forEach(action)

    fun dealInitialCards(cardDesk: CardDesk, printPlayer: (Player) -> Unit) {
        forEachPlayer { player ->
            repeat(2) { player.addCard(cardDesk.draw()) }
            printPlayer(player)
        }
    }

    fun processAdditionalCards(
        cardDesk: CardDesk,
        drawConditionStrategy: DrawConditionStrategy,
        printPlayer: (Player) -> Unit,
    ) {
        forEachPlayer { player ->
            while (player.canDrawCard() && drawConditionStrategy.shouldDraw(player.name)) {
                player.addCard(cardDesk.draw())
                printPlayer(player)
            }
        }
    }

    fun printResult(printResult: (Player) -> Unit) = forEachPlayer(printResult)

    init {
        require(allPlayers.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    companion object {
        fun from(playerNames: List<String>): Players {
            return Players(playerNames.map { Player(it) })
        }
    }
}
