package blackjack.error

object BlackjackErrorMessage {
    const val CAN_NOT_DRAW: String = "카드를 더 뽑을 수 없는 상태입니다"
    const val CAN_NOT_DRAW_THIS_CARD: String = "이 카드는 이미 뽑은 상태입니다"
    const val NOT_EXIST_CARD: String = "카드가 존재하지 않습니다."
    const val NO_MORE_REMAINING_CARDS: String = "남은 카드가 없습니다."
    const val DEALER_STATUS_CAN_NOT_BE_STAND: String = "딜러의 상태는 스탠드일 수 없습니다"
    const val NOT_INITIALIZED_PLAYER_PROFIT: String = "플레이어의 베팅 결과 금액이 계산되지 않았습니다"
}
