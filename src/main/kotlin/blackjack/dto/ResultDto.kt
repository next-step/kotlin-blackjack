package blackjack.dto

import blackjack.domain.participant.state.result.Result
import blackjack.domain.participant.state.role.Role

data class ResultDto(val name: String, val result: String) {
    companion object {
        fun from(role: Role, result: Result): ResultDto {
            return ResultDto(role.name.toString(), result.toString())
        }
    }
}
