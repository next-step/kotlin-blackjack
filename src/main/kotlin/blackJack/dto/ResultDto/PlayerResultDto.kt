package blackJack.dto.ResultDto

import blackJack.domain.result.PlayerResult

data class PlayerResultDto(val name: String, val profit: Int) {
    constructor(playerResult: PlayerResult) : this(playerResult.name, playerResult.profit)
}
