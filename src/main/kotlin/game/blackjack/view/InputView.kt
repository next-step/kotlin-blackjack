package game.blackjack.view

class InputView {

    fun readNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun readPlayerAction(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        var action: String
        do {
            action = readln()
        } while (!RESPONSES.contains(action))
        return action
    }

    companion object {
        val RESPONSES: Set<String> = setOf("y", "n")
    }
}
