package blackjack.business.util

object HitCommandEvaluator {

    fun evaluate(command: String): Boolean {
        return command == "y"
    }
}
