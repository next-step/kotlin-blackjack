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
                    dealer.hasBusted() && player.hasBusted().not() -> Result.WIN
                    player.hasBusted() -> Result.LOSE
                    player.sumOfCard() > dealerCardSum -> Result.WIN
                    else -> Result.LOSE
                }

            return PlayerOutcomes(player, result)
        }
    }
}
