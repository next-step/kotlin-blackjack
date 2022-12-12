package blackjack.view

import blackjack.domain.member.Player

private const val DELIMITERS = ","

private const val YES = "y"
private const val NO = "n"

object InputView {

    fun inputUsersNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln().split(DELIMITERS)
    }

    fun inputBetMoney(playerName: String): Int {
        println("${playerName}의 배팅 금액은?")

        return readln().toInt()
    }

    fun checkWantDrawMoreCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return inputYesOrNo() == YES
    }

    private fun inputYesOrNo(): String {
        while (true) {
            val input = readln()

            if (listOf(YES, NO).contains(input)) {
                return input
            }
        }
    }
}
