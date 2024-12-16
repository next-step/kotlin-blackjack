package blackjack.domain

import blackjack.domain.Participant.Player

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer().deal(gameMembers.allPlayers())
    }

    fun allPlayers(): Participants = gameMembers.allPlayers()

    fun playersWithOutDealer(): Participants = gameMembers.playersWithoutDealer()

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

    fun determineWinner(): List<PlayerOutcomes> {
        val dealerCardSum = gameMembers.dealer().sumOfCard()

        return gameMembers.playersWithoutDealer().allPlayers().map { PlayerOutcomes.from(it, dealerCardSum) }
    }

    fun calculateDealerWinningScore(): Int = determineWinner().filter { it.results == Result.LOSE }.size

    fun calculateDealerLoseScore(): Int = determineWinner().filter { it.results == Result.WIN }.size

    fun isDealerBust(): Boolean {
        return gameMembers.dealer().sumOfCard() > 21
    }
}
