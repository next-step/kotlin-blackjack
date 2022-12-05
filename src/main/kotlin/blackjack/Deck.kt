package blackjack

class Deck(
    _cards: MutableSet<Card>
) {
    private val queue = ArrayDeque<Card>()
    val size: Int
        get() = queue.size

    init {
        queue.addAll(_cards)
    }

    fun draw(): Card {
        check(queue.isNotEmpty()) { "뽑을 수 있는 카드가 없어요." }
        return queue.removeFirst()
    }

    companion object {
        fun init(): Deck {
            return Deck(
                Number.values().flatMap { number ->
                    Sharp.values().map { sharp ->
                        Card(number, sharp)
                    }
                }.toHashSet()
            )
        }
    }
}
