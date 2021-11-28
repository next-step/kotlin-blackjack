package blackjack.view.dto

import blackjack.domain.game.BlackJackResult
import blackjack.domain.game.GamerResult

data class BlackJackResultDto(val gamerResults: List<GamerResultDto>) {
    constructor(result: BlackJackResult) : this(
        gamerResults = result.gamerRestuls.map(::GamerResultDto)
    )
}

data class GamerResultDto(val profit: Int, val name: String) {

    constructor(result: GamerResult) : this(
        profit = result.profit.value,
        name = result.name.value,
    )
}

