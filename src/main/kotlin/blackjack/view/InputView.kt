package blackjack.view

object InputView {
    fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 임력하세요 (쉼표 기준으로 분리)")
        val names =
            readlnOrNull()?.trim()?.split(",")
                ?.map { it.trim() }
                ?: throw IllegalStateException("Invalid Player name")
        return names
    }

    fun readIsDrawMore(name: String): Boolean {
        println("$name 는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        val yesOrNo = readlnOrNull()?.trim() ?: "n"
        return yesOrNo == "y"
    }
}
