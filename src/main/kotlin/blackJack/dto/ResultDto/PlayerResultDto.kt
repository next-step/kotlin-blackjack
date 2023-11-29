package blackJack.dto.ResultDto

import blackJack.domain.result.PlayerResult

data class PlayerResultDto(val name: String, val result: String) {
    constructor(playerResult: PlayerResult) : this(playerResult.name, playerResult.result.result)
}
