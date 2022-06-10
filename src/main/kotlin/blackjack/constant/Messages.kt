package blackjack.constant

/**
 * 출력되는 메세지를 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object Messages {
    const val WRITE_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    const val HAND_OUT_CARD = "%s에게 2장의 카드를 나누었습니다."
    const val PRINT_HAVE_CARDS = "%s카드: "
}

object ErrorMessages {
    const val INPUT_IS_NULL_OR_BLANK = "입력은 빈칸이거나 NULL일 수 없습니다."
    const val NAME_IS_EMPTY = "이름은 빈칸일 수 없습니다."
}
