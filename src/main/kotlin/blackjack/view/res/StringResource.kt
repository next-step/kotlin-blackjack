package blackjack.view.res

import blackjack.model.Suit

fun getString(suit: Suit) = when (suit) {
    Suit.SPADE -> "스페이드"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLOVER -> "클로버"
}

object Resource {

    const val STR_FIRST_DRAW_CARD = "딜러와 %s에게 %d장의 카드를 나누었습니다."
    const val STR_RUNNING_DEALER_MORE_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다"
    const val STR_CARDS = "%s카드: %s"
    const val STR_RESULT_SCORE = " - 결과: %d"
    const val STR_FINAL_PROFIT = "## 최종 수익"
    const val STR_DEALER_PROFIT = "딜러: %d"

    const val STR_ASK_BET = "%s의 배팅 금액은?"
    const val STR_ASK_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    const val STR_ASK_DRAW = "%s은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
