package blackjack.domain.player

class Players(private val players: List<Player>) {
    constructor(vararg player: Player): this(player.toList())

    fun open(dealer: Dealer): Int = 0


}
