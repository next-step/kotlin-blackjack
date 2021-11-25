package blackjack.domain.exceptions

import java.lang.Exception

class ScoreOverflowException(override val message: String) : Exception(message)
