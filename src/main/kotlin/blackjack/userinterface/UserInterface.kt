package blackjack.userinterface

import blackjack.dto.GamerDto
import blackjack.dto.GamersDto
import blackjack.dto.ResultsDto

interface UserInterface {
    fun inputPlayerNames(): List<String>
    fun inputCardTakenWhether(playerName: String): Boolean
    fun outputCurrentCards(gamerDto: GamerDto)
    fun outputGamerCards(gamersDto: GamersDto)
    fun outputGameResult(resultDto: ResultsDto)
    fun outputDealerTaken(dealerLimitScore: Int)
}
