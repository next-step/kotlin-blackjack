package blackjack.domain

class Deck(
    cards: Set<Card>
) {
    private val _queue = ArrayDeque<Card>()
    val size: Int
        get() = _queue.size

    init {
        _queue.addAll(cards)
    }

    fun draw(): Card {
        check(_queue.isNotEmpty()) { "뽑을 수 있는 카드가 없어요." }
        return _queue.removeFirst()
    }

    fun drawInitAssignCards(): Cards {
        check(_queue.size > 2) { "카드가 두장 이상이어야 해요." }
        return Cards(draw(), draw())
    }

    companion object {
        fun init(): Deck {
            return Deck(
                Number.values().flatMap { number ->
                    Sharp.values().map { sharp ->
                        Card(number, sharp)
                    }
                }.shuffled()
                    .toSet()
            )
        }
    }
}
