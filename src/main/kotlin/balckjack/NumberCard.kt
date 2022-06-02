package balckjack

class NumberCard(
    override val pattern: CardPattern,
    private val number: Int
) : Card {

    init {
        require(number in 1..10) {
            "카드의 숫자는 1~10의 숫자중 하나여야 합니다. (입력:$number)"
        }
    }

    override fun count(): CardCount {
        return SingleCount(number)
    }
}
