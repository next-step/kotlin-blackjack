package blackJack.domain.card

interface CardScore {
    fun score(sum: Int = 0): Int
}
