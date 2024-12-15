package blackjack.domain

class Game(
    val players: Players,
) {
    init {
        players.dealer().deal(players)
    }

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> players.dealer().hit(player)
        HitCommand.STAY -> player.stay()
    }

    fun isPlayerStillPlaying(player: Player): Boolean {
        return player.hasBusted() && player.hasStayed().not()
    }
}
