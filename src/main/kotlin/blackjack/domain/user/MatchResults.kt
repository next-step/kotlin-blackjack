package blackjack.domain.user

/**
 * 게임 결과를 갖는 데이터 클래스.
 * Created by Jaesungchi on 2022.06.19..
 */
data class MatchResults(
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int
)
