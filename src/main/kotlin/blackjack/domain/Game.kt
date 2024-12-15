package blackjack.domain

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer().deal(gameMembers.allPlayers())
    }

    fun allPlayers(): Players = gameMembers.allPlayers()

    fun onlyPlayers(): Players = gameMembers.playersWithoutDealer()

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> gameMembers.dealer().hit(player)
        HitCommand.STAY -> player.stay()
    }

    fun isDealerCardSumLessThan16(): Boolean = gameMembers.dealer().sumOfCard() <= 16

    fun dealerHit() {
        gameMembers.dealer().hit(gameMembers.dealer())
    }

    fun isPlayerStillPlaying(player: Player): Boolean {
        return player.hasBusted() && player.hasStayed().not()
    }
}
