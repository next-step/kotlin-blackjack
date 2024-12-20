package blackjack.domain

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer().deal(gameMembers.allParticipants())
    }

    fun allPlayers(): Participants = gameMembers.allParticipants()

    fun participants(): Participants = gameMembers.playersWithoutDealer()

    fun dealer(): Participant.Dealer = gameMembers.dealer()

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

    fun determineDealerWinningOutcome(): DealerOutcomes {
        val dealer = gameMembers.dealer()
        val players = gameMembers.playersWithoutDealer()

        val dealerComparisonResults =
            players.allPlayers().map { player ->
                when {
                    dealer.sumOfCard() >= player.sumOfCard() -> Result.WIN
                    else -> Result.LOSE
                }
            }
        return DealerOutcomes(dealerComparisonResults)
    }

    fun isDealerBust(): Boolean {
        return gameMembers.dealer().sumOfCard() > 21
    }

    fun busted(participant: Participant) {
        participant.busted()
    }
}
