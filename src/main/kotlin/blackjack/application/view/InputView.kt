package blackjack.application.view

class InputView {

    fun getPlayers(): String {
        return readlnOrNull() ?: ""
    }

    fun getMoreCard(): Boolean {
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> false
        }
    }

}
