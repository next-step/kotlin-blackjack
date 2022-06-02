package blackjack

object InputValidator {
    fun checkYorN(input: String) {
        require(input == YES || input == NO)
    }
}
