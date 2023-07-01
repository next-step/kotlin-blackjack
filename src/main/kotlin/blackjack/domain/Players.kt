package blackjack.domain

data class Players(
    val values: List<Player>
) {
    constructor(vararg player: Player) : this(player.toList())

    fun receiveCards(dealer: Dealer): Players {
        return Players(values.map { player ->
            player.receiveCards(dealer.dealInitialCard())
        })
    }
}