package blackjack.view

object HitCommandEvaluator {

    fun evaluate(command: String): Boolean {
        return command == "y"
    }
}
