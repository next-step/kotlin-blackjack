package blackjack.view

import blackjack.model.player.Player

object InputView {
    private const val INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_ONE_MORE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private fun getQuestionAnswer(question: String): String {
        println(question)
        return readLine() ?: ""
    }

    fun getNames(): List<String> {
        val list = getQuestionAnswer(INPUT_NAMES).split(",")
        return if (list.isEmpty()) {
            getNames()
        } else {
            list
        }
    }

    fun getIsOneMore(player: Player): Boolean =
        when (getQuestionAnswer(player.name + INPUT_ONE_MORE)) {
            "y" -> true
            "n" -> false
            else -> {
                println("다시 입력하세요.")
                getIsOneMore(player)
            }
        }
}
