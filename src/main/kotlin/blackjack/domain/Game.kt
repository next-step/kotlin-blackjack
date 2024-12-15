package blackjack.domain

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer.deal(gameMembers.allPlayersWithDealer())
    }

    fun allPlayers(): Players = gameMembers.allPlayersWithDealer()

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> gameMembers.dealer.hit(player)
        HitCommand.STAY -> player.stay()
    }

    fun isPlayerStillPlaying(player: Player): Boolean {
        return player.hasBusted() && player.hasStayed().not()
    }
}
