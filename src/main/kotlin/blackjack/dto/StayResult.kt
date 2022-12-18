package blackjack.dto

import blackjack.utils.Validation

class StayResult(answer: String) {

    var isStay: Boolean = false
        private set

    init {
        require(Validation.isAnswer(answer)) { "y 또는 n이 아닙니다." }
        if (answer == "y") isStay = true
    }
}
