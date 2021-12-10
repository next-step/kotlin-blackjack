package blackjack.domain

object ExceptionTypes {
    const val WRONG_CARD_SIZE = "전체 카드 갯수가 맞지 않습니다."
    const val EMPTY_SHUFFLED_CARD_DECK = "모든 카드를 소모 하였습니다."
    const val INPUT_USER_NAMES_MUST_NOT_NULL_OR_EMPTY = "유저 이름은 비어서는 안됩니다."
    const val INPUT_BET_MUST_NOT_NULL_OR_EMPTY = "베팅 금랙은 비어서는 안됩니다."
    const val INPUT_ANSWER_FOR_MORE_CARD_MUST_NOT_NULL_OR_EMPTY = "카드 추가 여부에 관한 응답은 비어서는 안됩니다."
    const val INPUT_ANSWER_FOR_MORE_CARD_MUST_Y_OR_N = "카드 추가 여부에 관한 응답은 y 또는 n 이여야만 합니다."
}
