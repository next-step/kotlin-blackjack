package blackjack.presentation

class InputView {
    fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine().split(",")
    }

    fun inputHitOrStay(name: String): Boolean {
        println("${name}님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readLine()) {
            "y" -> {
                true
            }
            "n" -> {
                false
            }
            else -> {
                throw IllegalArgumentException("y 또는 n을 입력해주세요.")
            }
        }
    }

    private fun readLine(): String {
        return readlnOrNull() ?: throw IllegalStateException("입력값이 없습니다.")
    }
}
