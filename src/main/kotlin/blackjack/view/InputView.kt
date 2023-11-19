package blackjack.view

import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Players


object InputView {

    private const val DELIMITER = ","
    private const val PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DEFAULT_EXCEPTION_MESSAGE = "알 수 없는 예외가 발생하였습니다."

    fun readPlayers(): Players {
        println(PLAYER_MESSAGE)
        val userInput = readlnOrNull()
        return when (val blankValidationResult = validateIsNullOrBlank(userInput)) {
            is UserInput.Failure -> retry(blankValidationResult.errorMessage, ::readPlayers)
            is UserInput.Success -> createPlayers(blankValidationResult.data)
        }
    }

    private fun createPlayers(userInput: String): Players {
        val splitNames =userInput.split(DELIMITER)
        return try {
            createPlayers(splitNames)
        } catch (e: Exception) {
            return retry(e.message ?: DEFAULT_EXCEPTION_MESSAGE, ::readPlayers)
        }
    }

    private fun createPlayers(splitNames: List<String>): Players {
        val players = splitNames.map {
            val playerName = PlayerName(it.trim())
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
}
