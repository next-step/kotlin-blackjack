package blackjack.domain

class Dealer(
    private val _cards: List<BlackjackCard> = BlackjackCard.defaultSet.shuffled(),
) {
    private val cards
        get() = _cards.filterNot { it.isUsed }

    val cardsSize
        get() = cards.size

    fun drawCard(): BlackjackCard {
        val topOfCards = cards.firstOrNull()

        return topOfCards?.draw()
            ?: throw IllegalStateException("[Dealer] 더이상 남은 패가 존재하지 않습니다.")
    }

    infix fun initCardTo(player: Player): Player {
        repeat(INIT_CARD_COUNT) {
            player receive drawCard()
        }
        return player
    }

    infix fun giveCardTo(player: Player) {
        player receive drawCard()
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
