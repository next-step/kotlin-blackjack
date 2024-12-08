package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val hands: Hands = Hands(),
    override var status: ParticipantStatus = ParticipantStatus.PLAYING,
) : Participant(name, hands, status) {

    infix fun vs(player: Player): Result {
        return when {
            status == ParticipantStatus.BURST -> Result.LOSE
            player.status == ParticipantStatus.BURST -> Result.WIN
            score > player.score -> Result.WIN
            score < player.score -> Result.LOSE
            else -> Result.DRAW
        }
    }

    override fun initialDraw(deck: Deck) {
        super.initialDraw(deck)

        if (!Rule.checkDealerScoreThreshold(score)) {
            hit(deck)
        }

        handleStatus()
    }

    override fun handleStatus() {
        status =
            when {
                Rule.checkBurst(score) -> ParticipantStatus.BURST
                Rule.checkDealerScoreThreshold(score) -> ParticipantStatus.STAY
                else -> ParticipantStatus.PLAYING
            }
    }
}
