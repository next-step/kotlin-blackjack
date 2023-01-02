package domain

class Card(
    val type : CardType,
    val number: Int
){

}

enum class CardType(val order: Int) {
    HEART(0), SPACE(1), CLOVER(2), DIAMOND(3);

    companion object {
        fun fromInt(value: Int) = values().first { it.order == value }
    }
}