package blackjack.view

data class InputNames(
    val inputString: String
) {

    fun parseNames(): List<String> {
        return inputString.split(",")
    }
}
