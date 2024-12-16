package blackjack.domain

import blackjack.view.Result

data class PlayerOutcomes(val participant: Participant, val results: Result) {
    companion object {
        fun from(
            player: Participant,
            dealerCardSum: Int,
        ): PlayerOutcomes {
            val result =
                when {
                    player.sumOfCard() > dealerCardSum -> Result.WIN
                    else -> Result.LOSE
                }

            return PlayerOutcomes(player, result)
        }
    }
}
