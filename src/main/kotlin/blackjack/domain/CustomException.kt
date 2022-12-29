package blackjack.domain

/**
 * 가능한 모든 점수 조합이 21을 넘겼을 때
 */
class ScoreOverFlowException(
    message: String = "가능한 모든 점수 조합이 21점을 넘겼습니다 "
) : RuntimeException(message)

/**
 * 턴을 종료한 플레이어가 추가적인 행동을 할 경우
 */
class GameOverPlayerException(
    message: String = "이미 턴을 종료한 플레이어입니다."
) : RuntimeException(message)
