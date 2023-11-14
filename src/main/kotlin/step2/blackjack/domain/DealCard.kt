package step2.blackjack.domain

import step2.blackjack.domain.CardCount.getMinTotalCount
import step2.blackjack.model.Card
import step2.blackjack.model.User
import step2.blackjack.model.UserList
import step2.blackjack.model.vo.ChoiceIsDeal

/**
 * 카드 나누어 주는 객체
 * */
private const val ZERO_DEAL_COUNT = 0
private const val DEFAULT_DEAL_COUNT = 1
private const val STOP_DEAL_COUNT = 21

object DealCard {

    fun UserList.choiceDealCards(choiceDealListener: (User) -> String, drawStatusListener: (User) -> Unit) {
        this.userList.forEach { user ->
            user.choiceDealCard(choiceDealListener, drawStatusListener)
        }
    }

    private fun User.choiceDealCard(choiceDealListener: (User) -> String, drawStatusListener: (User) -> Unit) {

        if (STOP_DEAL_COUNT < this.cardList.getMinTotalCount()) return

        var choiceIsDeal = ChoiceIsDeal.from(choiceDealListener(this))

        var dealCount = ZERO_DEAL_COUNT

        while (choiceIsDeal.choiceIsDeal && STOP_DEAL_COUNT > this.cardList.getMinTotalCount()) {
            dealCount++
            this.dealCard()
            drawStatusListener(this)
            choiceIsDeal = ChoiceIsDeal.from(choiceDealListener(this))
        }

        if (choiceIsDeal.choiceIsDeal || dealCount == ZERO_DEAL_COUNT) drawStatusListener(this)
    }

    fun UserList.dealCard(count: Int = DEFAULT_DEAL_COUNT) {
        this.userList.forEach { user ->
            user.dealCard(count)
        }
    }

    fun User.dealCard(count: Int = DEFAULT_DEAL_COUNT) {
        repeat(count) {
            this.cardList + Card.random()
        }
    }
}
