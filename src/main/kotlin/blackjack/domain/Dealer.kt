package blackjack.domain

class Dealer {
    private val cardSet = BlackjackCard.defaultSet.shuffled()
        get() = field.asSequence()
            .filterNot { it.isUsed }
            .toList()

    val cardSize
        get() = cardSet.size

    fun drawCard(): BlackjackCard {
        return cardSet.firstOrNull()?.draw()
            ?: throw IllegalStateException("[Dealer] 더이상 남은 패가 존재하지 않습니다.")
    }
}
