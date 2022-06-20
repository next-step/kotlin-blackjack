package blackjack.view

import blackjack.common.PlayerDecision
import blackjack.common.PlayerProperty

object InputViewImpl : InputView {
    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BET_AMOUNT_QUESTION = "베팅 금액은?"
    private const val TAKE_A_CARD_QUESTION = "한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INVALID_PLAYER_DECISION = "y 또는 n 으로 답해주세요"
    private const val NOT_A_POSITIVE_NUMBER = "0 보다 큰 숫자를 입력해주세요"

    override fun getPlayerProperties(): List<PlayerProperty> {
        println(PLAYER_NAMES_QUESTION)
        val names = readln()
            .split(",")
            .toNonBlankStrings()

        return names.map {
            println("\n${it}의 $BET_AMOUNT_QUESTION")
            PlayerProperty(it, readln().toPositiveDouble())
        }
    }

    override fun getPlayerDecision(playerName: String): PlayerDecision {
        println("${playerName}은(는) $TAKE_A_CARD_QUESTION")

        while (true) {
            PlayerDecision
                .ofText(readln())
                ?.let { return(it) }
                ?: println(INVALID_PLAYER_DECISION)
        }
    }

    private fun List<String>.toNonBlankStrings(): List<String> = map { it.trim() }
        .filter { it.isNotBlank() }

    private fun String.toPositiveDouble(): Double = trim()
        .toDoubleOrNull()
        ?.takeIf { it > 0 }
        ?: throw IllegalArgumentException(NOT_A_POSITIVE_NUMBER)
}
