package blackjack.constant

/**
 * 출력되는 메세지를 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object Messages {
    const val WRITE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    const val HAND_OUT_CARD = "딜러와 %s에게 2장의 카드를 나누었습니다."
    const val PRINT_HAVE_CARDS = "%s카드: "
    const val WANT_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    const val PRINT_CARDS_AND_SCORE = "%s카드: %s - 결과: %s"
    const val PRINT_DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    const val FINAL_WIN_AND_LOSE = "## 최종 승패"
    const val DEALER_WIN_AND_LOSE = "딜러: %s승 %s무 %s패"
    const val USER_COLON = "%s: "
    const val WIN = "승"
    const val LOSE = "패"
    const val DRAW = "무"
    const val WRITE_BAT_MONEY = "%s의 배팅 금액은?"
}

object ErrorMessages {
    const val INPUT_IS_NULL_OR_BLANK = "입력은 빈칸이거나 NULL일 수 없습니다."
    const val NAME_IS_EMPTY = "이름은 빈칸일 수 없습니다."
    const val INPUT_IS_NOT_YES_OR_NO = "입력이 y나 n이 아닙니다."
    const val EMPTY_DECK = "모든 카드를 꺼냈습니다."
    const val USER_IS_EMPTY = "유저가 한명도 없습니다."
    const val BAT_MONEY_IS_NOT_NUMBER = "배팅금액이 숫자가 아닙니다."
}
