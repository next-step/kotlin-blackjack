package blackjack.ui

import blackjack.Participant

class ConsoleInput {
    companion object {
        fun inputPlayerNames(): List<String> {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            val playerNames = readln().split(",")
            println()
            return playerNames
        }

        fun inputBetAmount(player: Participant): Long {
            println("${player.name}의 베팅 금액은?")
            val betAmount = readln().toLong()
            println()
            return betAmount
        }

        fun inputDrawAnswer(player: Participant): DrawAnswer {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val answer = readln()
            return DrawAnswer.valueOf(answer.uppercase())
        }
    }
}
