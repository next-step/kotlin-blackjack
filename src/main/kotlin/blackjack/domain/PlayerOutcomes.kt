package blackjack.domain

data class PlayerOutcomes(val participant: Participant, val results: Result) {
    companion object {
        fun from(
            player: Participant,
            dealer: Participant,
        ): PlayerOutcomes {
            val dealerCardSum = dealer.sumOfCard()
            val result =
                when {
                    dealer.status == ParticipantStatus.BUSTED && player.status != ParticipantStatus.BUSTED -> Result.WIN
                    player.status == ParticipantStatus.BUSTED -> Result.LOSE
                    player.sumOfCard() > dealerCardSum -> Result.WIN
                    else -> Result.LOSE
                }

            return PlayerOutcomes(player, result)
        }
    }
}
