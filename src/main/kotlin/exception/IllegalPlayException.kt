package exception

class IllegalPlayException(message: String = "이미 끝난 플레이어는 더 이상 플레이가 불가능합니다.") : RuntimeException(message)
