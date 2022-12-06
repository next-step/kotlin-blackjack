package blackjack.domain.card

enum class CardNumber(val text: String, vararg val value: Int) {
    ACE("A", 1, 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    companion object {
        fun getRandomNumber() = values().random()

        fun calculate(numbers: List<CardNumber>): List<Int> {
            val sum = numbers.sumOf { it.value.first() }
            val countOfAce = numbers.count { it == ACE }

            val result = mutableListOf(sum)
            repeat(countOfAce) {
                result.add(result[it] + ACE.value[1] - ACE.value[0])
            }
            return result
        }
    }
}
