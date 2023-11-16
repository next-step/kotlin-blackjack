package blackjack.business

object HitCommandEvaluator {

    fun evaluate(command: String): Boolean {
        return command == "y"
    }
}
