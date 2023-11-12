package blackjack.view

class InputHandler {

    fun askForPlayerName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull() ?: throw IllegalArgumentException("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }
}
