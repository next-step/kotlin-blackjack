package blackjack.domain.player

import blackjack.view.dto.CardDto
import blackjack.view.dto.ParticiapntsDto
import blackjack.view.dto.ParticipantDto

class Participants(val participants: List<Participant>) {
    init {
        require(participants.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { PLAYERS_SIZE_EXCEPTION_MESSAGE }
    }

    fun findPlayer(name: String) = participants.find { it.name == name } ?: throw IllegalStateException(NOT_FOUND_PLAYER_EXCEPTION_MESSAGE)

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val PLAYERS_SIZE_EXCEPTION_MESSAGE = "블랙잭 플레이어는 1~6명 이어야 합니다."
        private const val NOT_FOUND_PLAYER_EXCEPTION_MESSAGE = "존재하지 않는 플레이어 입니다."

        fun toDto(participants: Participants): ParticiapntsDto {
            val playersDto =
                participants.participants.map {
                    ParticipantDto(
                        name = it.name,
                        cards = it.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
                    )
                }

            return ParticiapntsDto(playersDto)
        }
    }
}
