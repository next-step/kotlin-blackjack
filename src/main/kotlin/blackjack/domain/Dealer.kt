package blackjack.domain

class Dealer(
    private val _cardSet: List<BlackjackCard> = BlackjackCard.defaultSet.shuffled(),
) {
    private val cardSet
        get() = _cardSet.filterNot { it.isUsed }

    val cardSize
        get() = cardSet.size

    fun drawCard(): BlackjackCard {
        return cardSet.firstOrNull()?.draw()
            ?: throw IllegalStateException("[Dealer] 더이상 남은 패가 존재하지 않습니다.")
    }
}
