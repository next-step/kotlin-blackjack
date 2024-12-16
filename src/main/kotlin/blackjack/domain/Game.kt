package blackjack.domain

import blackjack.view.Result

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer().deal(gameMembers.allPlayers())
    }

    fun allPlayers(): Participants = gameMembers.allPlayers()

    fun playersWithOutDealer(): Participants = gameMembers.playersWithoutDealer()

    fun processPlayerTurn(
        participant: Participant,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> gameMembers.dealer().giveCardTo(participant)
        HitCommand.STAY -> participant.stay()
    }

    fun isDealerDrawCard(): Boolean = gameMembers.dealer().shouldDrawCard()

    fun giveCardToDealer() {
        gameMembers.dealer().giveCardTo(gameMembers.dealer())
    }

    fun isPlayerStillPlaying(participant: Participant): Boolean {
        return participant.hasBusted() && participant.hasStayed().not()
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
