package blackjack.userinterface

import blackjack.dto.PlayerDto
import blackjack.dto.GamerCardsDto
import blackjack.dto.ResultsDto

interface UserInterface {
    fun inputPlayerNames(): List<String>
    fun inputCardTakenWhether(playerName: String): Boolean
    fun outputCurrentCards(playerDto: PlayerDto)
    fun outputGamerCards(gamerCardsDto: GamerCardsDto)
    fun outputGameResult(resultDto: ResultsDto)
    fun outputDealerTaken(dealerLimitScore: Int)
}
