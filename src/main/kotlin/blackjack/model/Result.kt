package blackjack.model

enum class Result { WIN, LOSE, PUSH }

fun List<Result>.win() = count { it == Result.WIN }
fun List<Result>.lose() = count { it == Result.LOSE }
fun List<Result>.push() = count { it == Result.PUSH }
