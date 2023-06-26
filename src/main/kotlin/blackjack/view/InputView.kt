package blackjack.view

object InputView {

    fun inputNameOfPlayer(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        var names = readNames()
        while (names.size < 2){
            println("게임 참가자는 2명 이상이어야 합니다. 다시 입력해 주세요.")
            names = readNames()
        }
        return names
    }

    private fun readNames(): List<String> {
        return readlnOrNull()?.split(",") ?: emptyList()
    }
}
