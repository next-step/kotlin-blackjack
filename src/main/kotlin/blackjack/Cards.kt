package blackjack

class Cards(val values: List<Card>) {
    val scores: List<Int> = values
        .map { it.cardNumber.scores }
        .reduce { n1: List<Int>, n2: List<Int> ->
            n1.flatMap { i1 ->
                n2.map { i2 ->
                    i1 + i2
                }
            }
        }

    init {
        require(values.distinct().size == values.size) {
            "카드는 중복을 허용하지 않습니다."
        }
    }
}
