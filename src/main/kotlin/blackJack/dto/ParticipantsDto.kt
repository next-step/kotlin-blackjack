package blackJack.dto

import blackJack.domain.player.Participants

data class ParticipantsDto(val players: List<PlayerDto>, val dealer: DealerDto, val totalScore: Int) {
    constructor(participants: Participants) : this(players = PlayersDto(participants.players).playerDtos, dealer = DealerDto(participants.dealer))
}

