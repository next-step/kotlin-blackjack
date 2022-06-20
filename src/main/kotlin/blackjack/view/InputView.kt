package blackjack.view

class InputView {
    private var _inputPlayerAndBetting: MutableList<InputPlayerBetting> = mutableListOf()
    val inputPlayerAndBetting: List<InputPlayerBetting>
        get() = _inputPlayerAndBetting.toList()

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readlnOrNull() ?: throw IllegalArgumentException()
        return input.replace(" ", "").split(PLAYER_NAME_DELIMITER)
    }

    fun inputBetting(players: List<String>) {
        players.forEach {
            println("${it}의 배팅 금액은?")
            val bettingAmount = readlnOrNull() ?: throw IllegalArgumentException()
            _inputPlayerAndBetting.add(InputPlayerBetting(it, bettingAmount))
        }
    }

    companion object {
        const val PLAYER_NAME_DELIMITER = ","
    }
}
