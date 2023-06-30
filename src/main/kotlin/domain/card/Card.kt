package domain.card

interface Card {
    val denomination: Denomination
    val numbers: Set<Int>
        get() = denomination.numbers
}
