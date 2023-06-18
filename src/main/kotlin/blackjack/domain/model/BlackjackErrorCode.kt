package blackjack.domain.model

enum class BlackjackErrorCode(val message: (Array<Any>) -> String) {
    CAN_NOT_ADD_DUPLICATE_CARD(
        message = { "중복된 카드를 추가할 수 없습니다. 카드 : %s".format(*it) },
    ),
    CAN_NOT_PARTICIPATE_RANGE_OF_PLAYERS(
        message = { "참여 가능한 플레이어 범위가 아닙니다. 범위 : %s, 참여한 플레이어 수 : %s".format(*it) },
    ),
    MUST_BE_SET_INIT_CARD_COUNT(
        message = { "시작 플레이어 카드는 %s여야 합니다.".format(*it) },
    ),
    CAN_NOT_DONE_IN_THE_STATE(
        message = { "%s 상태에서는 할 수 없는 행위입니다.".format(*it) },
    ),
    CAN_NOT_USED_RANGE_OF_NAME_LENGTH(
        message = { "사용 가능한 이름 범위가 아닙니다. 범위 : %s, 입력한 이름 : %s".format(*it) },
    ),
}
