package blackjack

import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `모든 카드 출력하기`() {
        println(Deck.generate().joinToString())
    }
}

class Deck(private val type: Type, private val shape: Shape) {

    override fun toString(): String = "${type.nickName}${shape.nickName}"

    companion object {
        fun generate(): List<Deck> {
            val decks = mutableListOf<Deck>()
            for (type in Type.values()) {
                for (shape in Shape.values()) {
                    decks.add(Deck(type, shape))
                }
            }
            return decks
        }
    }
}

enum class Type(val nickName: String, points: List<Int>) {
    ACE("A", listOf(1, 11)),
    ONE("1", listOf(1)),
    TWO("2", listOf(2)),
    THREE("3", listOf(3)),
    FOUR("4", listOf(4)),
    FIVE("5", listOf(5)),
    SIX("6", listOf(6)),
    SEVEN("7", listOf(7)),
    EIGHT("8", listOf(8)),
    NINE("9", listOf(9)),
    TEN("10", listOf(10)),
    KING("K", listOf(10)),
    QUEEN("Q", listOf(10)),
    JACK("J", listOf(10))
}

enum class Shape(val nickName: String) {
    DIAMOND("다이아"),
    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드");
}
