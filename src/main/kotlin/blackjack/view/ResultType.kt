package blackjack.view

enum class ResultType(val message: String) {
    GAME_INIT("\n%s에게 2장의 나누었습니다."),
    PARTICIPANT_CARDS("%s카드: %s"),
    PARTICIPANT_RESULT("%s카드: %s - 결과: %d"),
}

