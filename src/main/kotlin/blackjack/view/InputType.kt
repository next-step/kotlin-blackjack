package blackjack.view
enum class InputType(val question: String) {
    PARTICIPANT_NAMES("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    CARD_ADD_QUESTION("\n%s은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
}
