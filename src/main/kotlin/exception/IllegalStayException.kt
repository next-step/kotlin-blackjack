package exception

class IllegalStayException(message: String = "Hit 을 제외한 상태에서는 stay 가 불가능합니다.") : RuntimeException(message)
