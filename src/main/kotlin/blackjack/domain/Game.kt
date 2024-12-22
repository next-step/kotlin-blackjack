package blackjack.domain

class Game(
    private val gameMembers: GameMembers,
) {
    init {
        gameMembers.dealer.deal(gameMembers.allParticipants())
    }

    fun allPlayers(): Participants = gameMembers.allParticipants()

    fun members() = gameMembers.playersWithoutDealer().members

    fun dealer(): Participant.Dealer = gameMembers.dealer

    fun players(): List<Participant.Player> = gameMembers.players

    fun processPlayerTurn(
        participant: Participant,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> gameMembers.dealer.giveCardTo(participant)
        HitCommand.STAY -> participant.stay()
    }

    fun isDealerDrawCard(): Boolean = gameMembers.dealer.shouldDrawCard()

    fun giveCardToDealer() {
        gameMembers.dealer.giveCardTo(gameMembers.dealer)
    }

    fun isPlayerStillPlaying(participant: Participant): Boolean {
        return participant.status == ParticipantStatus.PLAYING
    }

    fun determineWinner(): List<PlayerOutcomes> {
        return gameMembers.playersWithoutDealer().members.map { PlayerOutcomes.from(it, gameMembers.dealer) }
    }

    fun determineDealerWinningOutcome(): DealerOutcomes {
        val dealer = gameMembers.dealer
        val players = gameMembers.playersWithoutDealer().members

        val dealerComparisonResults =
            players.map { player ->
                when {
                    dealer.status == ParticipantStatus.BUSTED -> Result.LOSE
                    player.status == ParticipantStatus.BUSTED -> Result.WIN
                    dealer.sumOfCard() >= player.sumOfCard() -> Result.WIN
                    else -> Result.LOSE
                }
            }
        return DealerOutcomes(dealerComparisonResults)
    }
}
