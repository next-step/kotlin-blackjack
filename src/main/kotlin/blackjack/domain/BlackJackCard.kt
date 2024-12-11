package blackjack.domain

class BlackJackCard {
    private var value = 0
        set(value) {
            field = (value - (value / 13) * 13)
        }
    var cardType = CardType.CLOVER
    val spacial: SpacialCard?
        get() =
            when (this.value) {
                1 -> SpacialCard.ACE
                11 -> SpacialCard.JACK
                12 -> SpacialCard.QUEEN
                0 -> SpacialCard.KING
                else -> null
            }

    constructor(value: Int) {
        this.value = value
        this.cardType =
            when (value / 13) {
                0 -> CardType.SPADE
                1 -> CardType.CLOVER
                2 -> CardType.DIAMOND
                3 -> CardType.HEART
                else -> CardType.SPADE
            }
    }

//    constructor(value: Int, type: CardType, spacial: SpacialCard) {
//        this.value =  value
//        this.cardType = type
// //        this.spacial = spacial
//    }

    enum class CardType(val koreanText: String) {
        CLOVER("클로버"),
        HEART("하트"),
        SPADE("스페이드"),
        DIAMOND("다이아"),
    }

    enum class SpacialCard(val spacialText: String) {
        ACE("A"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
    }

    fun getPoint(): Int {
        return when (spacial) {
            SpacialCard.ACE -> 1
            SpacialCard.JACK, SpacialCard.QUEEN, SpacialCard.KING -> 10
            else -> value
        }
    }

    override fun toString(): String {
        val valueString = spacial?.spacialText ?: value.toString()
        return "$valueString${cardType.koreanText}"
    }
}

fun List<BlackJackCard>.toString() = this.joinToString(separator = "\n")
