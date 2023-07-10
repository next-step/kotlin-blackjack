package blackjack.domain

data class Players(
    val values: List<Player>
) {
    constructor(vararg player: Player) : this(player.toList())

    fun receiveDefaultCards(dealer: Dealer): Players {
        return Players(
            values.map { player ->
                player.receiveCards(newCards = dealer.dealDefaultCard())
                player
            }
        )
    }

    fun gameResults(dealer: Dealer): List<GameResult> {
        return values.map {
            GameResult.resultOfPlayer(it, dealer)
        }
    }
}
