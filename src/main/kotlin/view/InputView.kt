package view

class InputView {
    fun getUserName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun getExtraCard(name: String): Boolean {
        var inputValue = ""
        while (inputValue != YES && inputValue != NO) {
            println("${name}님 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            inputValue = readln()
        }
        return inputValue == YES
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
    }
}
