package blackjack.domain

class Dealer(private val cardDeck: CardDeck) {
    private val cards = Cards(cardDeck.next(), cardDeck.next())
    val openedCards = cards.values[0]
    val hands
        get() = cards.values

    fun obtain() {
        require(isObtainable()) { "딜러는 카드를 발급받을 수 없습니다." }
        cards.add(cardDeck.next())
    }

    fun isObtainable(): Boolean {
        return cards.sum() < 17
    }

    fun compareWith(player: Player): CompareResult {
        if (cards.sum() > 21) {
            return CompareResult.DEALER_LOSE
        }
        if (player.sumOfCards() > 21) {
            return CompareResult.DEALER_WIN
        }

        if (cards.sum() == player.sumOfCards()) {
            return CompareResult.DRAW
        }
        if (cards.sum() < player.sumOfCards()) {
            return CompareResult.DEALER_LOSE
        }
        return CompareResult.DEALER_WIN
    }
}
