package blackJack.dto.playerDto

import blackJack.domain.player.Participants

data class ParticipantsDto(val players: List<PlayerDto>, val dealer: DealerDto) {
    constructor(participants: Participants) : this(players = PlayersDto(participants.players).playerDtos, dealer = DealerDto(participants.dealer))
}
