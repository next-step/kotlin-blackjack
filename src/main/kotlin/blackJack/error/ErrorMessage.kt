package blackJack.error

enum class ErrorMessage(val message: String) {
    EMPTY_INPUT("값이 비어 있습니다!"),
    WRONG_SUIT("잘못된 카드 모양입니다."),
    WRONG_RANK("잘못된 카드 숫자입니다."),
    EMPTY_NAME("이름이 비어 있습니다!"),
    EMPTY_CARD("카드가 비어 있습니다!"),
    EMPTY_CARDS("카드리스트가 비어 있습니다!"),
    WRONG_INPUT("y or n 만 입력 가능합니다."),
    DUPLICATE_CARDS("중복된 카드가 있습니다."),
    EMPTY_PLAYERS("플레이어가 비어 있습니다.")
    ;
}
