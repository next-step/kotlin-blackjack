package blackJack.dto.ResultDto

import blackJack.domain.result.Result

data class ResultDto(val playersResult: PlayersResultDto, val dealerResult: DealerResultDto) {

    constructor(result: Result) : this(
        PlayersResultDto(result.playersResult.playersResult.map { PlayerResultDto(it) }),
        DealerResultDto(result.dealerResult.win, result.dealerResult.lose)
    )
}

