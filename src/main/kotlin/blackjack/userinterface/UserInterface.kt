package blackjack.userinterface

import blackjack.dto.GamerDto
import blackjack.dto.ResultDto

interface UserInterface {
    fun inputPlayerNames(): List<String>
    fun inputCardTakenWhether(playerName: String): Boolean
    fun outputCurrentCards(gamerDto: GamerDto)
    fun outputGamerCards(gamerDto: List<GamerDto>)
    fun outputGameResult(result: List<ResultDto>)
    fun outputDealerTaken(dealerLimitScore: Int)
}
