package blackjack.ui

import blackjack.Player

class ConsoleInput {
    companion object {
        fun inputPlayerNames(): List<String> {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            val playerNames = readln().split(",")
            return playerNames
        }

        fun inputDrawAnswer2(player: Player): DrawAnswer {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val answer = readln()
            return DrawAnswer.valueOf(answer.uppercase())
        }
    }
}
