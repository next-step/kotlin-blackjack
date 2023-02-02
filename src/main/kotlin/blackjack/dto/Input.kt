package blackjack.dto

import blackjack.domain.bet.Money
import blackjack.domain.participant.state.Name

interface Input {
    fun inputPlayer(): Array<Name>

    fun inputBettingMoney(name: String): Money

    fun inputHitOrStay(name: String): Boolean
}
