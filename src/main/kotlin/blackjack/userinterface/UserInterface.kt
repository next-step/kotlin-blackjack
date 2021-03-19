package blackjack.userinterface

import blackjack.dto.PlayerDto
import blackjack.dto.ResultDto

interface UserInterface {
    fun inputPlayerNames(): List<String>
    fun inputCardTakenWhether(playerName: String): Boolean
    fun outputPlayerCards(playerDto: List<PlayerDto>)
    fun outputGameResult(result: List<ResultDto>)
}
