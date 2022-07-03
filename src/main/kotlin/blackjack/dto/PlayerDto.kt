package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Stat
import blackjack.domain.user.Player

class PlayerDto(
    name: String,
    cards: List<Card>,
    score: Int,
    val stat: Stat
) : UserDto(name, cards, score) {
    companion object {
        fun of(player: Player, stat: Stat): PlayerDto {
            return PlayerDto(
                name = player.name,
                cards = player.cards(),
                score = player.getScore().value,
                stat = stat
            )
        }
    }
}
