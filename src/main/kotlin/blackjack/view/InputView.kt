package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.ParticipantName
import blackjack.domain.player.Players
import java.util.Locale

object InputView {

    private const val DELIMITER = ","
    private const val PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DRAW_CARD_MESSAGE_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val DEFAULT_EXCEPTION_MESSAGE = "알 수 없는 예외가 발생하였습니다."

    fun readPlayers(): Players {
        println(PLAYER_MESSAGE)
        val userInput = readlnOrNull()
        return when (val blankValidationResult = validateIsNullOrBlank(userInput)) {
            is UserInput.Failure -> retry(blankValidationResult.errorMessage, ::readPlayers)
            is UserInput.Success -> createPlayers(blankValidationResult.data)
        }
    }

    fun readDrawMoreCard(player: Player): YesOrNo {
        println(String.format(System.lineSeparator() + DRAW_CARD_MESSAGE_FORMAT, player.name.value))
        val userInput = readlnOrNull()
        return when (val blankValidationResult = validateIsNullOrBlank(userInput)) {
            is UserInput.Failure -> retry(blankValidationResult.errorMessage) { readDrawMoreCard(player) }
            is UserInput.Success -> validateYesOrNo(blankValidationResult, player)
        }
    }

    private fun createPlayers(userInput: String): Players {
        val splitNames = userInput.split(DELIMITER)
        return try {
            createPlayers(splitNames)
        } catch (e: Exception) {
            return retry(e.message ?: DEFAULT_EXCEPTION_MESSAGE, ::readPlayers)
        }
    }

    private fun createPlayers(splitNames: List<String>): Players {
        val players = splitNames.map {
            val playerName = ParticipantName(it.trim())
            Player(name = playerName)
        }
        return Players(players)
    }

    private fun <T> retry(errorMessage: String, retryFunction: () -> T): T {
        println(errorMessage)
        return retryFunction()
    }

    private fun validateIsNullOrBlank(userInput: String?): UserInput {
        if (userInput.isNullOrBlank()) {
            return UserInput.Failure("입력값이 존재하지 않습니다.")
        }
        return UserInput.Success(userInput)
    }

    private fun validateIsYesOrNo(userInput: String): UserInput {
        return when (userInput) {
            YesOrNo.YES.value, YesOrNo.YES.value.uppercase(Locale.getDefault()),
            YesOrNo.NO.value, YesOrNo.NO.value.uppercase(Locale.getDefault()) -> UserInput.Success(userInput)
            else -> UserInput.Failure(
                "${YesOrNo.YES.value}(${YesOrNo.YES.value.uppercase(Locale.getDefault())}) " +
                    "또는 ${YesOrNo.NO.value}(${YesOrNo.NO.value.uppercase(Locale.getDefault())})만 입력 가능합니다."
            )
        }
    }

    private fun validateYesOrNo(blankValidationResult: UserInput.Success, player: Player): YesOrNo {
        val isDrawCard = blankValidationResult.data
        return when (val yesOrNoValidationResult = validateIsYesOrNo(isDrawCard)) {
            is UserInput.Failure -> retry(yesOrNoValidationResult.errorMessage) { readDrawMoreCard(player) }
            is UserInput.Success -> YesOrNo.of(yesOrNoValidationResult.data)
        }
    }

    enum class YesOrNo(val value: String) {
        YES("y"),
        NO("n");

        fun isNo(): Boolean {
            return this == NO
        }

        companion object {
            fun of(userInput: String): YesOrNo {
                return values().first { it.value == userInput || it.value.uppercase(Locale.getDefault()) == userInput }
            }
        }
    }
}
