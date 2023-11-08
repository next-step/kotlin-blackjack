package blackjack.view

import blackjack.domain.Player

object InputView {

    fun inputPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun inputHitOrStand(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val value = readln()
        runCatching { require(value == "y" || value == "n") }
            .onFailure {
                println("y 또는 n을 입력해주세요.")
                inputHitOrStand(player)
            }

        if(yesOrNo(value) && player.score() > 21) {
            println("${player.name}는 이미 21점을 초과했습니다.")
            return false
        }
        return yesOrNo(value)
    }

    private fun yesOrNo(input: String): Boolean {
        return input == "y"
    }
}
