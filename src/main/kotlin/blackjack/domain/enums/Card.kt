package blackjack.domain.enums

enum class Card(private val type: String, private val value: Int) {
    A("A", 11),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ;

    fun getType(): String = type

    fun getValue(): Int = value

    companion object {
        fun fromType(type: String): Card? {
            return Card.entries.find { it.type == type }
        }
    }
}
