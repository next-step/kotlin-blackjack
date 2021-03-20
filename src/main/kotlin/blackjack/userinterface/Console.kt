package blackjack.userinterface

import blackjack.dto.PlayerDto
import blackjack.dto.ResultDto

object Console : UserInterface {

    private const val PLAYER_NAME_DELIMITER = ","

    override fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readLine() ?: throw RuntimeException("입력값 오류")
        return input.split(PLAYER_NAME_DELIMITER).map { it.trim() }.also {
            validateDuplicateName(it)
        }
    }

    private fun validateDuplicateName(playerNames: List<String>) {
        if (playerNames.size != playerNames.distinct().size) {
            throw RuntimeException("입력한 이름에 중복이 있어요. 입력한 이름: $playerNames")
        }
    }

    override tailrec fun inputCardTakenWhether(playerName: String): Boolean {
        println("$playerName 는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return when (val input = readLine()) {
            "y" -> true
            "n" -> false
            else -> {
                println("올바르지 않은 입력입니다. 입력값: $input")
                inputCardTakenWhether(playerName)
            }
        }
    }

    override fun outputCurrentCards(playerDto: PlayerDto) {
        println(playerDto.viewFormat())
    }

    override fun outputPlayerCards(playerDto: List<PlayerDto>) {
        println("${playerDto.joinToString(", ") { it.name }} 에게 2장의 카드를 나누었습니다.")
        playerDto.forEach { println(it.viewFormat()) }
    }

    override fun outputGameResult(result: List<ResultDto>) {
        result.forEach { println("${it.name}카드: ${it.cards.joinToString()} - 결과: ${it.score}") }
    }

    private fun PlayerDto.viewFormat(): String = "${this.name}카드: ${this.cards.joinToString()}"
}
