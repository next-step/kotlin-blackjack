package blackjack.domain.player

import blackjack.BLACKJACK_COUNT

enum class HandStatus(val message: String) {
    BLACKJACK("블랙잭"),
    BUST("버스트"),
    GENERAL("카드를 더 받을 수 있습니다.");

    companion object {

        fun of(score: Int): HandStatus = when {
            score == BLACKJACK_COUNT -> BLACKJACK
            score > BLACKJACK_COUNT -> BUST
            else -> GENERAL
        }
    }
}
