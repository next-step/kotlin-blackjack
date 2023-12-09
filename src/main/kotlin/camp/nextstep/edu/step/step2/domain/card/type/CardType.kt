package camp.nextstep.edu.step.step2.domain.card.type

enum class CardType(
    val type: String
) {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");


    companion object {
        fun of(type: String): CardType {
            return CardType.values().find { it.type == type }
                ?: throw IllegalArgumentException("존재하지 않는 카드 타입입니다.")
        }
    }

}
