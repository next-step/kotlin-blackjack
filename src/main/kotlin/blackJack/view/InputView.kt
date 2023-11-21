package blackJack.view

object InputView {
    private const val PLAYER_QUERY_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun getNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readlnOrNull() ?: throw IllegalArgumentException("콘솔 입력을 확인해 주세요.")

        return input.replace(" ", "")
            .split(",")
    }

    fun getPlayerInput(playerName: String): String {
        println(PLAYER_QUERY_FORMAT.format(playerName))

        val input = readlnOrNull() ?: throw IllegalArgumentException("콘솔 입력을 확인해 주세요.")
        require(input == "y" || input == "n") ?: throw IllegalArgumentException("y 또는 n을 입력해 주세요.")

        return input
    }
}