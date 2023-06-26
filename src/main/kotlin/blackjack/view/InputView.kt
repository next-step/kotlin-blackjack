package blackjack.view

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun receivePlayerNames(): List<String> {
        return receiveString().split(PLAYER_NAME_DELIMITER)
    }

    private fun receiveString(): String {
        var input: String? = null

        do {
            input = readlnOrNull()
        } while (input.isNullOrBlank())

        return input
    }
}
