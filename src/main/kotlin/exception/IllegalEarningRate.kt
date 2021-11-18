package exception

class IllegalEarningRate(message: String = "끝나지 않은 상태에서는 earning rate 조회가 불가능합니다.") : RuntimeException(message)
