package blackjack.enums

enum class CardSymbol(private val type: String) {
    ACE("에이스"),
    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    ;

    fun getTye() = type

    companion object {
        fun fromType(type: String): CardSymbol? {
            return entries.find { it.type == type }
        }
    }
}
