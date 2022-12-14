package nextstep.blackjack.dto

import nextstep.blackjack.Player

data class PlayerResultDto(
    val name: String,
    val cards: List<String>,
    val totalPoint: Int
) {
    companion object {
        fun from(player: Player) = PlayerResultDto(
            player.name,
            player.cards.map { it.description },
            player.calculateTotalPoint()
        )
    }
}
