package blackject.model.card

/**
 * 카드 한장에 대한 정보 관리
 * */
data class Card(
    val type: CardType,
    val number: CardNumber
) {
    val cardName: String
        get() = number.numberName + type.cardName
}
