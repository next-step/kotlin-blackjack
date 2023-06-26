package blackjack.domain

abstract class CardExceptions(message: String = "", throwable: Throwable? = null) : RuntimeException(message, throwable)

class BadCardPickException : CardExceptions()