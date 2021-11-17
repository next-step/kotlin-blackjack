package exception

class IllegalDrawException(message: String = "Hit 을 제외한 상태에서는 draw 가 불가능합니다.") : RuntimeException(message)
