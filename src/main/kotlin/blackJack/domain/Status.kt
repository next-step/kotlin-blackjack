package blackJack.domain

import blackJack.domain.Answer.*

enum class Status {
    HIT,
    BUST,
    STAND,
    BLACKJACK,
    ;

    companion object {
        fun calculateStatus(score: Int, answer: Answer = y): Status {
            return when {
                score > 21 -> BUST
                score == 21 -> BLACKJACK
                answer == n -> STAND
                else -> HIT
            }
        }

        fun addCardValidation(status: Status) {
            if (status == BUST || status == BLACKJACK || status == STAND) {
                throw IllegalArgumentException("${status.name} 상태에서는 카드를 추가할 수 없습니다.")
            }
        }
    }
}
