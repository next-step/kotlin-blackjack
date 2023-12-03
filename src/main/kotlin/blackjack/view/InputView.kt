package blackjack.view

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import java.util.Locale

object InputView {

    private const val DELIMITER = ","
    private const val PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DRAW_CARD_MESSAGE_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val DEFAULT_EXCEPTION_MESSAGE = "알 수 없는 예외가 발생하였습니다."

    fun readPlayers(dealer: Dealer): Players {
        println(PLAYER_MESSAGE)
        val userInput = readlnOrNull()
        return when (val blankValidationResult = validateIsNullOrBlank(userInput)) {
            is UserInput.Failure -> retry(blankValidationResult.errorMessage) { readPlayers(dealer) }
            is UserInput.Success -> createPlayers(blankValidationResult.data, dealer)
        }
    }

    fun readHitOrStay(player: Player): HitOrStay {
        println(String.format(System.lineSeparator() + DRAW_CARD_MESSAGE_FORMAT, player.name()))
        val userInput = readlnOrNull()
        return when (val blankValidationResult = validateIsNullOrBlank(userInput)) {
            is UserInput.Failure -> retry(blankValidationResult.errorMessage) { readHitOrStay(player) }
            is UserInput.Success -> validateHitOrStay(blankValidationResult, player)
        }
    }

    private fun createPlayers(userInput: String, dealer: Dealer): Players {
        val playerNames = userInput.split(DELIMITER)
        return try {
            Players.create(playerNames, dealer)
        } catch (e: Exception) {
            return retry(e.message ?: DEFAULT_EXCEPTION_MESSAGE) { readPlayers(dealer) }
        }
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

    private fun validateHitOrStay(blankValidationResult: UserInput.Success, player: Player): HitOrStay {
        val isDrawCard = blankValidationResult.data
        return when (val yOrNValidationResult = validateIsYOrN(isDrawCard)) {
            is UserInput.Failure -> retry(yOrNValidationResult.errorMessage) { readHitOrStay(player) }
            is UserInput.Success -> HitOrStay.of(yOrNValidationResult.data)
        }
    }

    private fun validateIsYOrN(userInput: String): UserInput {
        return when (userInput) {
            HitOrStay.Hit.value, HitOrStay.Hit.value.uppercase(Locale.getDefault()),
            HitOrStay.Stay.value, HitOrStay.Stay.value.uppercase(Locale.getDefault()) -> UserInput.Success(userInput)

            else -> UserInput.Failure(
                "${HitOrStay.Hit.value}(${HitOrStay.Hit.value.uppercase(Locale.getDefault())}) " +
                        "또는 ${HitOrStay.Stay.value}(${HitOrStay.Stay.value.uppercase(Locale.getDefault())})만 입력 가능합니다."
            )
        }
    }

    enum class HitOrStay(val value: String) {
        Hit("y"),
        Stay("n");

        fun isStay(): Boolean {
            return this == Stay
        }

        companion object {
            fun of(userInput: String): HitOrStay {
                return values().first { it.value == userInput || it.value.uppercase(Locale.getDefault()) == userInput }
            }
        }
    }
}
