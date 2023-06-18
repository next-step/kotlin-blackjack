package blackjack.domain.model

enum class BlackjackErrorCode(val message: (Array<Any>) -> String) {
    CAN_NOT_USED_RANGE_OF_NAME_LENGTH(
        message = { "사용 가능한 이름 범위가 아닙니다. 범위 : %s, 입력한 이름 : %s".format(*it) },
    ),
}
