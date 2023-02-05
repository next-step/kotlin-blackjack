package blackjack.dto

import blackjack.domain.participant.state.role.Role

data class ResultDto(val name: String, val profit: Int) {
    companion object {
        fun from(role: Role, profit: Int): ResultDto {
            return ResultDto(role.name.toString(), profit)
        }
    }
}
