package blackjack.view

import blackjack.domain.HitCommand
import blackjack.domain.Participant

object InputView {
    fun getPlayerNames(): List<String> {
        println(INPUT_PLAYER_MESSAGE)
        val input = readln()
        return parsePlayerNamesOrThrow(input)
    }

    private fun parsePlayerNamesOrThrow(input: String?): List<String> {
        require(!input.isNullOrBlank()) { CANNOT_EMPTY_OR_NULL }
        return input.split(INPUT_PLAYER_DELIMITER).map { it.trim() }
    }

    fun askHitOrStay(participant: Participant): HitCommand {
        require(participant is Participant.Player) { "Only players can make a hit or stay decision." }

        println(String.format(HIT_MESSAGE, participant.name))
        val input = readlnOrNull() ?: throw IllegalArgumentException("Input cannot be null")

        return parseHitCommandOrThrow(input)
    }

    private fun parseHitCommandOrThrow(input: String): HitCommand =
        when (input) {
            "y" -> HitCommand.HIT
            "n" -> HitCommand.STAY
            else -> throw IllegalArgumentException(INVALID_HIT_COMMAND)
        }

    fun askBettingAmount(playerName: String): Int {
        println(String.format(INPUT_BETTING_AMOUNT, playerName))
        val input = readln()
        return parseAmountOrThrow(input)
    }

    private fun parseAmountOrThrow(input: String?): Int {
        require(!input.isNullOrBlank()) { CANNOT_EMPTY_OR_NULL }
        return input.toIntOrNull() ?: throw IllegalArgumentException(INVALID_AMOUNT)
    }

    private const val INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_PLAYER_DELIMITER = ","
    private const val CANNOT_EMPTY_OR_NULL = "입력값은 null이거나 비어 있을 수 없습니다."
    private const val HIT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INVALID_HIT_COMMAND = "y또는 n만 입력해주세요."
    private const val INPUT_BETTING_AMOUNT = "%s의 배팅 금액은?"
    private const val INVALID_AMOUNT = "숫자만 입력할 수 있습니다."
}
