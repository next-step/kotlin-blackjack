package blackjack.domain

import blackjack.NO
import blackjack.YES

object InputValidator {
    fun checkYorN(input: String) {
        require(input == YES || input == NO)
    }
}
