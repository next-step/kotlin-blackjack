package exception

class IllegalBetException(message: String = "베팅 금액은 양수이어야 합니다.") : RuntimeException(message)
