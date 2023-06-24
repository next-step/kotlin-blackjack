package blackjack.domain

import blackjack.domain.Game.Companion.INIT_TAKE_SIZE
import blackjack.domain.state.OutcomeStateContextHolder
import blackjack.domain.state.StateType

class Dealer(deck: Deck = Deck()) :
    Player(
        deck = deck,
        name = DEFAULT_DEALER_NAME
    ),
    GameOutcomeCalculator {
    override fun addCard(card: Card) {
        require(isAddable()) {
            "현재 점수가 $THRESHOLD 이상이거나 카드를 분배받은 적이 있는 경우 추가할 수 없습니다."
        }.run { super.addCard(card) }
    }

    override fun isAddable(): Boolean = deck.score() <= THRESHOLD && deck.size <= INIT_TAKE_SIZE

    override fun calculate(players: Players): GameResult {
        val dealerType = StateType.from(deck)

        players.forEach {
            calculateByState(dealerType = dealerType, gamer = it as Gamer)
        }

        return GameResult(
            dealerRecord = this.revenue,
            playerRecords = players.map { it.name to it.revenue }
        )
    }

    private fun calculateByState(dealerType: StateType, gamer: Gamer) {
        val playerType = StateType.from(gamer.deck)

        OutcomeStateContextHolder
            .find(playerType = playerType, dealerType = dealerType)
            .update(gamer = gamer, dealer = this)
    }

    companion object {
        const val THRESHOLD = 16
        const val DEFAULT_DEALER_NAME = "딜러"
    }
}
