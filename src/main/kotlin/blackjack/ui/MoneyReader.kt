package blackjack.ui

import blackjack.domain.Money
import blackjack.domain.player.PlayerName

object MoneyReader {
    fun read(playerName: PlayerName): Money {
        println("${playerName.value}의 배팅 금액은?")
        return Money(readln().toInt())
    }
}
