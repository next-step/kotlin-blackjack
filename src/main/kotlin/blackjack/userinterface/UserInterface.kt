package blackjack.userinterface

import blackjack.dto.Dto
import blackjack.dto.ResultDto

interface UserInterface {
    fun inputPlayerNames(): List<String>
    fun inputCardTakenWhether(playerName: String): Boolean
    fun outputPlayerCards(dto: List<Dto>)
    fun outputGameResult(result: List<ResultDto>)
}
