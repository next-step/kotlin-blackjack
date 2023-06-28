package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player

class BlackJackRoundManager(val dealer: Dealer) {
    fun round(player: Player) {
        var answer: RoundAnswer = RoundAnswer.y

        while(answer == RoundAnswer.y) {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            answer = RoundAnswer.valueOf(readln())

            if (answer == RoundAnswer.y) {
                val dealingCard = dealer.dealing()
                player.hit(dealingCard)
                PlayerHandPrinter.print(player)
            } else {
                player.stay()
            }
        }
    }
}

enum class RoundAnswer { y, n }
