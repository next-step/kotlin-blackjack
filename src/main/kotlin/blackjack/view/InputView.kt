package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

object InputView {

    fun createDealer(): Dealer {
        println("딜러 이름을 입력해주세요. (미입력시 이름은 딜러가 됩니다.)")
        val dealer = readlnOrNull()
        val dealerName = if (dealer.isNullOrEmpty()) {
            DEALER
        } else {
            dealer
        }
        return Dealer(dealerName)
    }

    fun createParticipants(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participants = readln().split(",")
        return participants.map {
            println("${it}의 배팅 금액은?")
            val amount = readln().toInt()
            Player(it, amount, drawable = GameView)
        }
    }

    private const val DEALER = "딜러"
}
