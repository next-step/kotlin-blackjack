package blackjack.domain

class BlackjackCard private constructor(
    val number: CardNumber,
    val emblem: Emblem,
    private var _isUsed: Boolean = false,
) {
    val isUsed: Boolean
        get() = _isUsed

    fun draw(): BlackjackCard {
        check(_isUsed.not()) {
            error("[BlackjackCard] 이미 사용된 카드입니다. | card: $this")
        }
        _isUsed = true
        return this
    }

    enum class Emblem { 하트, 다이아, 클로버, 스페이드 }

    enum class CardNumber(
        val score: Set<Int>,
    ) {
        A(setOf(1, 11)),
        `1`(setOf(1)),
        `2`(setOf(2)),
        `3`(setOf(3)),
        `4`(setOf(4)),
        `5`(setOf(5)),
        `6`(setOf(6)),
        `7`(setOf(7)),
        `8`(setOf(8)),
        `9`(setOf(9)),
        K(setOf(10)),
        Q(setOf(10)),
        J(setOf(10)),
        ;
    }

    override fun toString(): String = "$number$emblem"

    companion object {
        val defaultSet = CardNumber.entries.flatMap { number ->
            Emblem.entries.map { emblem ->
                BlackjackCard(number, emblem)
            }
        }
    }
}
