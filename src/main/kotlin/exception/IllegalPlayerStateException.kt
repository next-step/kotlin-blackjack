package exception

class IllegalPlayerStateException(message: String = "존재할 수 없는 플레이어 상태입니다.") : RuntimeException(message)
