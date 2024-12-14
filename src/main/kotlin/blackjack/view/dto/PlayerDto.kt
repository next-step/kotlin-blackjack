package blackjack.view.dto

import blackjack.domain.participant.Player

data class PlayerDto(
    val name: String,
    val bettingAmount: Int,
)

fun PlayerDto.toDomain() = Player(
    name = this.name,
    bettingAmount = this.bettingAmount,
)
