package blackjack.dto

import blackjack.domain.Blackjack

data class BlackjackDto(
    val players: List<PlayerDto>,
    val dealer: DealerDto
)

fun Blackjack.toDto() = BlackjackDto(
    players = players.map { PlayerDto.of(it, dealer.compareTo(it).opposite()) },
    dealer = DealerDto.of(dealer, players.groupingBy { dealer.compareTo(it) }.eachCount())
)
