package blackjack.view

import blackjack.entity.Player

object InputView {
    private const val DEALER_NAME = "딜러"

    fun getDealerName(): String {
        return this.DEALER_NAME
    }

    fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun getBetAmount(name: String): Int {
        println("${name}의 배팅 금액은?")
        return readln().toIntOrNull() ?: throw RuntimeException("베팅금액은 숫자를 입력해주세요.")
    }

    fun isGameContinue(player: String): Boolean {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val continueYN = readln()
        return "Y" == continueYN.uppercase()
    }

    fun playerInfo(player: Player) {
        println("${player.name}카드는 ${player.hand.showCards()}")
    }

    fun dealerAddCardComment(dealerName: String) {
        println("${dealerName}는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
