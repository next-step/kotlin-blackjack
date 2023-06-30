package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerName

class BlackJackRoundManager(val dealer: Dealer) {

    fun printOpenMessage(playerNames: List<PlayerName>) {
        println("${playerNames.joinToString(separator = ", ")}에게 2장의 나누었습니다.")
    }
    fun round(player: Player) {
        val playerName: PlayerName = player.name
        var answer: RoundAnswer = RoundAnswer.y

        while(answer == RoundAnswer.y) {
            println("${playerName.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            answer = RoundAnswer.valueOf(readln())

            if (answer == RoundAnswer.y) {
                dealer.dealing(player)
                PlayerHandPrinter.print(player)
            }

        }
    }
}

enum class RoundAnswer { y, n }
