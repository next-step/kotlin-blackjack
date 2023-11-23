package blackJack.domain

enum class Status {
    HIT,
    BUST,
    STAND,
    BLACKJACK,
    ;

    companion object {
        fun calculateStatus(score: Int, cardSize: Int): Status {
            return when {
                score > 21 -> BUST
                score == 21 && cardSize == 2 -> BLACKJACK
                else -> HIT
            }
        }

        fun validationAddCard(status: Status) {
            if (status == BUST || status == BLACKJACK || status == STAND) {
                throw IllegalArgumentException("${status.name} 상태에서는 카드를 추가할 수 없습니다.")
            }
        }
    }
}
