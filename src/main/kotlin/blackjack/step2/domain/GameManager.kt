package blackjack.step2.domain

import blackjack.step2.domain.Cards.Companion.BLACKJACK_SCORE

class GameManager(private val dealer: Dealer) {
    fun dealFirstCards(players: Players): PlayerCards {
        return dealer.dealFirstCards(players)
    }

    fun dealCardIfValid(playerCard: PlayerCard): PlayerCard {
        if (playerCard.calculateScore() >= BLACKJACK_SCORE) {
            println("${playerCard.playerName}의 점수가 21 이상입니다. 카드를 더 받을 수 없습니다.")
            return playerCard
        }
        return dealer.dealCard(playerCard)
    }
}
