package blackjack.domain

import blackjack.domain.card.Card

class Dealer : BlackJackGamer {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator()
    private var winRecord = (GameRecordType.WIN to 0)
    private var loseRecord = (GameRecordType.LOSE to 0)
    private var drawRecord = (GameRecordType.DRAW to 0)

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }

    override fun getGamerType(): GamerType {
        return GamerType.DEALER
    }

    override fun getName(): String {
        return DEALER_NAME
    }

    fun getTotalGameRecord(): List<Pair<GameRecordType, Int>> {
        return listOf(winRecord, loseRecord, drawRecord)
    }

    override fun proceedGameRecord(gameRecordType: GameRecordType) {
        when (gameRecordType) {
            GameRecordType.WIN -> winRecord = Pair(GameRecordType.WIN, winRecord.second + 1)
            GameRecordType.LOSE -> loseRecord = Pair(GameRecordType.LOSE, loseRecord.second + 1)
            GameRecordType.DRAW -> drawRecord = Pair(GameRecordType.DRAW, drawRecord.second + 1)
            GameRecordType.NONE -> throw IllegalArgumentException("게임 결과는 NONE이 되면 안됩니다.")
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val CONDITION_TO_DEALER_DRAW_CARD = 16
    }
}
