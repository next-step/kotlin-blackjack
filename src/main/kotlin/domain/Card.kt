package domain

data class Card(val pattern: CardPattern, val score: CardValue) {

    enum class CardPattern(val patternName: String) {
        SPADE("스페이드"),
        DIAMOND("다이아몬드"),
        HEART("하트"),
        CLUB("클로버");
    }

    enum class CardValue(val valueName: String, val point: Int) {
        ACE("A", 1),
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
        KING("K", 10);
    }

    companion object {
        fun issueAllCards(): Set<Card> {
            return ArrayList<Card>().apply {
                CardPattern.values().forEach { p ->
                    this.addAll(CardValue.values().map { Card(p, it) })
                }
            }.shuffled().toHashSet()
        }
    }
}