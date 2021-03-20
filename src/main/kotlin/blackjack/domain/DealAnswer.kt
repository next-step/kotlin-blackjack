package blackjack.domain

import java.lang.IllegalArgumentException

enum class DealAnswer(val answer: String) {
    YES("y"), NO("n");

    companion object {
        fun select(answer: String): DealAnswer {
            return values().find { it.answer == answer }
                ?: throw IllegalArgumentException("카드 수락 여부 입력이 잘못된 형식입니다.");
        }
    }
}