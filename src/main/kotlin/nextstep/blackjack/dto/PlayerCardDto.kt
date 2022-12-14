package nextstep.blackjack.dto

import nextstep.blackjack.Player

data class PlayerCardDto(
    val name: String,
    val cardNames: List<String>
) {
    companion object {
        fun from(player: Player) = PlayerCardDto(
            player.name,
            player.cards.map { card -> card.description }
        )
    }
}

