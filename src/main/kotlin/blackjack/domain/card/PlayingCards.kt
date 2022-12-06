package blackjack.domain.card

class PlayingCards(list: List<PlayingCard>) {
    private val _list: MutableList<PlayingCard> = list.toMutableList()
    val list: List<PlayingCard>
        get() = _list.toList()

    fun get(): PlayingCard {
        if (_list.isEmpty()) {
            throw NoSuchElementException("카드가 없습니다.")
        }
        return _list.removeAt(0)
    }

    fun add(card: PlayingCard) {
        if (_list.contains(card)) {
            throw IllegalArgumentException("중복된 카드는 추가할 수 없습니다.")
        }
        _list.add(card)
    }
}
