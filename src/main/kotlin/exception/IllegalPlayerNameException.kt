package exception

class IllegalPlayerNameException(message: String = "사람의 이름은 비어있을 수 없습니다.") : RuntimeException(message)
