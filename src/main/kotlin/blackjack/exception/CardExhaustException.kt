package blackjack.exception

class CardExhaustException(
    override val message: String
) : RuntimeException(message)
