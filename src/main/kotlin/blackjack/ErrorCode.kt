package blackjack

enum class ErrorCode(val message: String) {
    INVALID_SCORE_VALUE("유효하지 않은 Score 값입니다"),
    INVALID_PLAYER_RECEIVE_RESPONSE("y 또는 n 만 입력할 수 있습니다.")
}
