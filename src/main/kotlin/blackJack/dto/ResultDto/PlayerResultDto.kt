package blackJack.dto.ResultDto

import blackJack.domain.result.PlayerResult

data class PlayerResultDto(val name: String, val bettingPrice: Int, val result: Double) {
    constructor(playerResult: PlayerResult) : this(playerResult.name, playerResult.bettingPrice, playerResult.result.reward)
}
