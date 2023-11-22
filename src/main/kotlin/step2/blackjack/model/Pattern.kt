package step2.blackjack.model

enum class Pattern {
    SPACE, CLOVER, HEART, DIAMOND;

    companion object {
        fun random(): Pattern = Pattern.values().random()

        infix fun Pattern.to(sign: Sign): Card = Card.of(this, sign)
    }
}