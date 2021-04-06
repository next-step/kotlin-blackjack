package blackjack.view

class InputView {
    companion object {
        tailrec fun playerNames(): String {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

            return readLine() ?: playerNames()
        }

        fun askIfPlayerWantToMoreCard(name: String): Boolean {
            println("$name 는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)")

            val answer = readLine()!!.toLowerCase()

            if (answer == "y") {
                return true
            } else if (answer == "n") {
                return false
            }

            throw IllegalArgumentException("잘못된 입력값입니다.")
        }
    }
}
