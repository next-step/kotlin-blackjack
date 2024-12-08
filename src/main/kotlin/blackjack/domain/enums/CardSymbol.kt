package blackjack.domain.enums

enum class CardSymbol(private val type: String) {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    ;

    fun getType(): String = type

    companion object {
        fun fromType(type: String): CardSymbol? {
            return entries.find { it.type == type }
        }
    }
}
