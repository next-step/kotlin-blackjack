package exception

class IllegalScoreException(message: String = "끝나지 않은 상태에서는 score 조회가 불가능합니다.") : RuntimeException(message)
