package blackjack.view

object InputView {
    private val messageBuilder = StringBuilder()

    fun readNames(): List<String> {
        messageBuilder.append("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        print(messageBuilder.toString())

        val input = readlnOrNull()?.trim() ?: throw IllegalArgumentException("입력이 없습니다.")
        require(input.isNotEmpty()) { "이름은 최소 1개 이상 입력해야 합니다." }
        return input.split(",").map { it.trim() }
    }

    fun readBetAmounts(names: List<String>): List<Int> {
        messageBuilder.clear()
            .append("각 플레이어가 배팅할 금액을 입력하세요.")
        print(messageBuilder.toString())

        return names.map { name ->
            messageBuilder.clear()
                .append(name)
                .append("의 배팅 금액은?")
            print(messageBuilder.toString())

            readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
        }
    }

    fun readMoreCard(name: String): Boolean {
        while (true) {
            println(buildPromptMessage(name))

            val userInput = readlnOrNull()?.trim()?.lowercase()
            if (isValidInput(userInput)) {
                return userInput == "y"
            } else {
                println("y 또는 n만 입력해주세요.")
            }
        }
    }

    private fun buildPromptMessage(name: String): String {
        return "$name 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }

    private fun isValidInput(input: String?): Boolean {
        return input == "y" || input == "n"
    }

    private fun print(message: String) {
        println(message)
    }
}
