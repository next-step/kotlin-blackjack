package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Players

object GameResultCalculator {
    // 딜러와 각 플레이어의 점수를 비교해서 승패를 판별하고 기록함
    // 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다
    fun getResult(dealer: Dealer, players: Players) {
        val scoreOfDealer = dealer.getScore()
        val isDealerLose = scoreOfDealer > BlackJackGame.MAX_SCORE
        players.players.forEach {
            val scoreOfPlayer = it.getScore()
            it.getGameResult(isDealerLose || scoreOfPlayer > scoreOfDealer)
            dealer.getGameResult(!isDealerLose && scoreOfDealer > scoreOfPlayer)
        }
    }
}
