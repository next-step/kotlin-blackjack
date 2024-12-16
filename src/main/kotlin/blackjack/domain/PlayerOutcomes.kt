package blackjack.domain

import blackjack.domain.Participant.Player

data class PlayerOutcomes(val player: Player, val results: Result) {
    companion object {
        fun from(
            player: Player,
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
