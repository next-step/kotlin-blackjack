package blackjack.domain

class Game(
    private val dealer: Dealer,
    val players: Players,
) {
    init {
        dealer.deal(players)
    }

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> dealer.hit(player)
        HitCommand.STAY -> player.stay()
    }

    fun isPlayerStillPlaying(player: Player): Boolean {
        return player.hasBusted() && player.hasStayed().not()
    }
}
